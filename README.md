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

##### - Install Python

[Windows](http://timmyreilly.azurewebsites.net/python-flask-windows-development-environment-setup/), [Mac](http://docs.python-guide.org/en/latest/starting/install/osx/), [Linux](https://docs.aws.amazon.com/cli/latest/userguide/awscli-install-linux-python.html)


##### - Add the dependency to use library in project:
  ```
     implementation 'com.github.vijaybharatigate6:iris_recognition:1.0'  
  ```  
 
## Run Project

Once all the settings of project are configured. To start import.

```shell
   import com.gate6.g6_iris_recognition.g6_irisRecognition;
   import com.gate6.g6_iris_recognition.utilsPkg.CallBackListiners;
   import com.gate6.g6_iris_recognition.utilsPkg.RequestType;
   import android.content.Context;
```

After import, need to implements CallBackListiners in class & add implement method also in your code:

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
      public void onCompleteResponse(int RequestTypee, JSONObject irisData, List irislist) {
          if (RequestTypee == RequestType.IRIS_IMAGE_CHECK_REQUEST) {
              Log.v("iris1", "irisData"+irisData+" irislist = "+irislist);
          }else if (RequestTypee == RequestType.IRIS_REGISTER_REQUEST) {
              Log.v("iris2", "irisData"+irisData);
          }else if (RequestTypee == RequestType.IRIS_RECOGNITION_REQUEST) {
              Log.v("iris3", "irisData"+irisData);
          }
      }
  }    
```

Once model is trained then its ready to test with real-time images:

```shell
   iris_name = G6_iris_recognition.iris_model_test(test_encoding_model_path,real_time_image_path) 
   test_encoding_model_path   ===>  encodingModel/irisEncodings.pickle
   real_time_image_path       ===>  real-time_image_path
   iris_name                  ===>  it returns predicted person name if image matches with trained image model person image & if not then it returns name as unmatch.
```


## Requirements :

  * Need clearer images from the scanner.
  * Images shouldn't capture on direct sunlight.
  * Person shouldn't use glass or lens on eye scanning.
  * All scanned images need to be on same shapes/size(eg - 320x240).
  * As per image size and quality/noise, need to change parameter of filters according.
  * 90% above eye iris need to be capture on image taken from scanner.
  * Need min 5 clearer images to train a model.
  * After all this done according, set threshold of Hamming Distance to recognize.



## Support

If you face any issue in configuration or usage with Gate6 Iris Recognition Package as per the instruction documented above. Please feel free to communicate with Gate6 Iris Recognition Package development team.

