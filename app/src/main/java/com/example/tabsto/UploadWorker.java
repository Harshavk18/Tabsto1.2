package com.example.tabsto;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UploadWorker extends AppCompatActivity {
    private static final String TAG="UploadWorker";
    //declare variables
    private String[] FilePathStrings;
    private String[] FileNameStrings;
    private File[] listFile;
    File file;
    DatabaseReference reff;
    Button btnUpDirectory,btnSDCard;
    ArrayList<String> pathHistory;
    String lastDirectory;
    int count=0;
    ArrayList<XYValue> uploadData;
    ListView lvInternalStorage;
    XYValue user;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_worker);
        lvInternalStorage = (ListView) findViewById(R.id.lvInternalStorage);
        btnUpDirectory = (Button) findViewById(R.id.btnUpDirectory);
        btnSDCard = (Button) findViewById(R.id.btnViewSDCard);
        uploadData = new ArrayList<>();
        checkFilePermissions();
        user=new XYValue();
        reff= FirebaseDatabase.getInstance().getReference("XYValue");
        lvInternalStorage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lastDirectory = pathHistory.get(count);
                if(lastDirectory.equals(adapterView.getItemAtPosition(i))){
                    readExcelData(lastDirectory);
                }
                else{
                    count++;
                    pathHistory.add(count,(String)adapterView.getItemAtPosition(i));
                    checkInternalStorage();
                    Log.d(TAG,"lvInternalStorage: "+pathHistory.get(count));
                }
            }
        });
        //Goes up one directory level
        btnUpDirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count == 0){
                    Log.d(TAG, "btnUpDirectory: You have reached the highest level directory.");
                }else{
                    pathHistory.remove(count);
                    count--;
                    checkInternalStorage();
                    Log.d(TAG, "btnUpDirectory: " + pathHistory.get(count));
                }
            }
        });

        //Opens the SDCard or phone memory
        btnSDCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
                pathHistory = new ArrayList<String>();
                pathHistory.add(count,System.getenv("EXTERNAL_STORAGE"));
                Log.d(TAG, "btnSDCard: " + pathHistory.get(count));
                checkInternalStorage();
            }
        });
    }

    private void readExcelData(String filePath) {
       Log.d(TAG,"readExcelData:Reading Excel File");
       File inputFile=new File(filePath);
       try{
           InputStream inputStream = new FileInputStream(inputFile);
           XSSFWorkbook workbook=new XSSFWorkbook(inputStream);
           XSSFSheet sheet=workbook.getSheetAt(0);
           int rowCount = sheet.getPhysicalNumberOfRows();
           FormulaEvaluator formulaEvaluator=workbook.getCreationHelper().createFormulaEvaluator();
           StringBuilder sb=new StringBuilder();
           for(int r=1; r<rowCount; r++){
               Row row=sheet.getRow(r);
               int cellsCount = row.getPhysicalNumberOfCells();
               String id=reff.push().getKey();
               for(int c=0;c<cellsCount;c++){
                   if(c>8){
                       Log.e(TAG,"readExcelData: ERROR. Excel File Format is incorrect");
                       toastMessage("ERROR: Excel File Format is incorrect");
                       break;
                   }
                   else{
                        String value=getCellsAsString(row,c,formulaEvaluator);
                       if(c==0) {
                           user.setX(Double.parseDouble(value));
                       }
                       else if(c==1){
                           user.setName(value);
                       }
                       else if(c==2){
                           user.setType(value);
                       }
                       else if(c==3){
                           user.setIncharge(value);
                       }
                       else if(c==4){
                           user.setDuration(value);
                       }
                       else if(c==5){
                           user.setDepartment(value);
                       }
                       else if(c==6){
                           String day=value.substring(0,2);
                           double d=Double.parseDouble(day);
                           if(d>31){
                               value=value.substring(1);
                           }
                           user.setDate(value);
                       }
                       else if(c==7){
                           user.setVenue(value);
                       }
                       reff.child(id).setValue(user);
                        String cellInfo = "r:" +r +"c:" + c+"; v:" +value;
                        Log.d(TAG,"readExcelData : Data from row :"+cellInfo);
                        sb.append(value+" ;");
                   }
               }
               sb.append(" ; ");
               parseStringBuilder(sb);
           }
       }
       catch (FileNotFoundException e){
           Log.e(TAG,"readExcelData: FileNotFoundException."+e.getMessage());
       }
       catch(IOException e){
           Log.e(TAG,"readExcelData: Error reading inputStream"+e.getMessage());
       }
    }

    private void parseStringBuilder(StringBuilder sb) {
        Log.d(TAG,"parseStringBuilder: Started Parsing");
        String[] rows=sb.toString().split(":");
        for(int i=0;i<rows.length;i++){
            String[] columns = rows[i].split(",");
            try{
                double x=Double.parseDouble(columns[0]);
                String Name=columns[1];
                String Type=columns[2];
                String Incharge=columns[3];
                String duration=columns[4];
                String department=columns[5];
                String date=columns[6];
                String venue=columns[7];
                String cellinfo=Name+" "+Type+" "+Incharge+" "+duration+" "+department+" "+date+" "+venue;
                Log.d(TAG,"parseStringBuilder: Data from row: "+cellinfo);
                uploadData.add(new XYValue(x,Name,Type,Incharge,duration,department,date,venue));

            }catch(NumberFormatException e){
                Log.e(TAG,"parseStringBuilder : NumberFormatException "+e.getMessage());
            }
        }
        printDataToLog();
    }

    private void printDataToLog() {
        Log.d(TAG,"printDataToLog: Printing data to log...");
        for(int i=0;i<uploadData.size(); i++){
            double x=uploadData.get(i).getX();
            String Name=uploadData.get(i).getName();
            String Type=uploadData.get(i).getType();
            String Incharge=uploadData.get(i).getIncharge();
            String duration=uploadData.get(i).getDuration();
            String department=uploadData.get(i).getDepartment();
            String date=uploadData.get(i).getDate();
            String Venue=uploadData.get(i).getVenue();
            Log.d(TAG,"printdataToLog: ("+ x +" "+Name+" "+Type+" "+Incharge+" "+duration+" "+department+" "+date+" "+Venue);
        }
        Toast.makeText(UploadWorker.this,"data Inserted Successfully",Toast.LENGTH_LONG).show();
    }

    private String getCellsAsString(Row row, int c, FormulaEvaluator formulaEvaluator) {
        String value="";
        try{
            Cell cell=row.getCell(c);
            CellValue cellValue=formulaEvaluator.evaluate(cell);
            switch(cellValue.getCellType()){
                case Cell.CELL_TYPE_BOOLEAN:
                    value = ""+cellValue.getBooleanValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    double numericValue=cellValue.getNumberValue();
                    if(HSSFDateUtil.isCellDateFormatted(cell)){
                        double date = cellValue.getNumberValue();
                        SimpleDateFormat formatter = new SimpleDateFormat("DD/MM/YY");
                        value=formatter.format(HSSFDateUtil.getJavaDate(date));
                    }else{
                        value=""+numericValue;
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    value=""+cellValue.getStringValue();
                    break;
                default:

            }
        }catch(NullPointerException e ){
            Log.e(TAG,"getCellsAsString: NullPointerException :"+e.getMessage());
        }
        return value;
    }

    private void checkInternalStorage() {
        Log.d(TAG,"checkInternalStorage: Started.");
        try{
            if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                toastMessage("No SD card found.");
            }
            else{
                file=new File(pathHistory.get(count));
                Log.d(TAG,"checkInternalStorage: directory path: " + pathHistory.get(count));
            }
            listFile=file.listFiles();
            FilePathStrings = new String[listFile.length];
            FileNameStrings = new String[listFile.length];
            for(int i=0;i<listFile.length;i++){
                FilePathStrings[i]=listFile[i].getAbsolutePath();
                FileNameStrings[i] = listFile[i].getName();
            }
            for(int i=0;i<listFile.length;i++){
                Log.d("Files","FileName:" + listFile[i].getName());
            }
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,FilePathStrings);
            lvInternalStorage.setAdapter(adapter);

        } catch (NullPointerException e) {
            Log.e(TAG,"chekcInternalStorage: NULLPOINTEREXCEPTION "+e.getMessage());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkFilePermissions() {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            int permissionCheck = this.checkSelfPermission("Manifest.permission.READ_EXTERNAL_STORAGE");
            permissionCheck+=this.checkSelfPermission("Manifest.permission.WRITE_EXTERNAL_STORAGE");
            if(permissionCheck!=0){
                this.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},1001);
            }
            else{
                Log.d(TAG,"checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
            }
        }
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
