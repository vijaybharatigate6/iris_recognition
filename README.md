[![](https://jitpack.io/v/vijaybharatigate6/iris_recognition.svg)](https://jitpack.io/#vijaybharatigate6/iris_recognition)

# Gate6 Iris Recognition Package
   G6_iris_recognition is a module for eye iris recognition.
   
## Installation needs before installing package module 
   ##### Add it in your root build.gradle at the end of repositories:
  ```
   allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```  
 
## Installation

#####  Add the dependency to use library in project:
  ```
     implementation 'com.github.vijaybharatigate6:iris_recognition:1.1'  
  ```  
 
## Run Project

Once all the settings of the project are configured. Then start with import:

```shell
   import com.gate6.g6_iris_recognition.g6_irisRecognition;
   import com.gate6.g6_iris_recognition.utilsPkg.CallBackListiners;
   import com.gate6.g6_iris_recognition.utilsPkg.RequestType;
   import android.content.Context;
```

After import, need to implement CallBackListiners in class & add its method in class:

```shell
   public class ClassName extends AppCompatActivity implements CallBackListiners {
      private Context mContext;
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.ClassLayoutNam);
          mContext=this;
      }
      @Override
      public void onCompleteResponse(int RequestTypee, JSONObject irisData, List iris_image_encoding) {
          if (RequestTypee == RequestType.IRIS_IMAGE_CHECK_REQUEST) {
	      irischeck_image_encoding=iris_image_encoding;
              Log.v("iris1", "irisData"+irisData+" irislist = ");
          }else if (RequestTypee == RequestType.IRIS_REGISTER_REQUEST) {
              Log.v("iris2", "irisData"+irisData);
          }else if (RequestTypee == RequestType.IRIS_RECOGNITION_REQUEST) {
              Log.v("iris3", "irisData"+irisData);
          }
      }
  }    
```

#### 1. Use following method to test iris image and get image encoding:

```shell
   g6_irisRecognition.getInstance().iris_check(mContext,image_path,this);
   
   - Get response on implemented onCompleteResponse method with request type IRIS_IMAGE_CHECK_REQUEST
```

#### 2. After stage 1, use following method to register a user for iris recognition:

```shell
   g6_irisRecognition.getInstance().iris_registration(mContext,companyId,userName,imageDataList,this);
   
   companyId        ===>  company id where you want register.
   userName         ===>  need unique username(email).after iris recognition in responce return we get username of detected person.
   imageDataList    ===>  In list add 3 iris image check encoding we get in 1st stage.
                          eg-: imageDataList=[iris_check_image1_Encoding,iris_check_image2_Encoding,iris_check_image3_Encoding]
   - Get response on implemented onCompleteResponse method with request type IRIS_REGISTER_REQUEST
```

#### 3. After stage 2, use following method to check image output for iris recognition:

```shell
   g6_irisRecognition.getInstance().iris_recognition(mContext,companyId,path,this);
   
   companyId        ===>  company id where you want register.
   - Get response on implemented onCompleteResponse method with request type IRIS_RECOGNITION_REQUEST
```


## Requirements :

  * Need clearer images from the scanner.
  * Images shouldn't capture on direct sunlight.
  * Person shouldn't use glass or lens on eye scanning.
  * All scanned images need to be on same shapes/size(eg - 320x240).
  * As per image size and quality/noise, need to change parameter of filters according.
  * 90% above eye iris need to be capture on image taken from scanner.
  * Need min 3 clearer images to train a model.
  * After all this done according, set threshold of Hamming Distance to recognize.



## Support

If you face any issue in configuration or usage with Gate6 Iris Recognition Package as per the instruction documented above. Please feel free to communicate with Gate6 Iris Recognition Package development team.

