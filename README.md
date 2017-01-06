# :star: JAVABEANS delivery time calculator 1.0 :star:
##Description
This micro service can calculate the travel time by car based on [Google Maps APIs]
(https://developers.google.com/maps/documentation/distance-matrix/start).
You can use this API with browser or java code *(1.8 SDK)*, for more info look at the Usage section.
Our program can calculate distance inside a continent, for more info look at the Output section.
* If you find any bug, error or something else, please send a feedback for us!
* If you like it, please share with orthers! :wink:

##Usage
To use our delivery time calculator micro service, you need to clone the repository, and change some things in the code.
######Information
1. In the **APIService** class you find the calcTime() method, which takes two arguments, which are used to calculate the delivery time and work with Google API.
2. The 2 arguments are defined in the **APIController**
    * **ORIGIN** -> change this to set the base location (where your store is)
    * **destination** -> change this to set the destination (where to send the delivery)
3. If everything works out :thumbsup:, the result is in a JSON Object, the "time" key holds the value in milliseconds and
  the "status" key holds a string.

######Running
1. Run and compile the TimeGeneratorService *(default port: 60003)*. After that you can choose between two options.

  2.a Open the following link: http://0.0.0.0:60003/api/timecalculator/[TARGET_LOCATION]

  2.b Write a main method. For example:

  ```
  public class Main {
      public static void main(String[] args) {
          try {
              APIController apiController = new APIController(APIService.getInstance());
              System.out.println(apiController.getTimeInMs("[TARGET_LOCATION]"));
          } catch (IOException|URISyntaxException|JSONException e) {
              e.printStackTrace();
          }
      }
  }
  ```

##Output
There is 3 scenarios, that could happen:

1. The 2 location exists, so you get the estimated delivery time in milliseconds

2. The 2 locations can't be reached by car (oversea, anything else), it means that the time willd be **ZERO** and the status **ZERO_RESULTS**

3. One of the locations are not valid (typo, anything else), it means that the time will be **ZERO** again and the status **NOT_FOUND**

_(**NOTE: **In every case you can see the status value inside the JSON!)_

###### Browser:
![timecalcapi_browser](https://cloud.githubusercontent.com/assets/19217964/21643269/ac0de082-d287-11e6-9781-dd4ba350a7fc.png)
###### Console:
![timecalcapi_console](https://cloud.githubusercontent.com/assets/19217964/21643280/b53340bc-d287-11e6-9123-183002034180.png)

###### Thank you for using our service! :heart_eyes_cat: