# :star: JAVABEANS delivery time calculator :star:

To use our delivery time calculator micro service, you need to clone the repository, and change some things in the code

1. In the **APIService** class you find the calcTime() method, which takes two arguments, which are used to calculate the delivery time
2. The 2 arguments are defined in the **APIController**
    * **origin** -> change this to set the base location (where your store is) 
    * **destination** -> change this to set the destination (where to send the delivery)
3. After passing the right attributes to the apiService.calcTime method, your delivery time gets calculated
4. There is 3 scenarios, that could happen:
    1. The 2 location exists, so you get the estimated delivery time in milliseconds
    2. The 2 locations can't be reached by car (oversea, anything else), it means that the time can **NOT** be calculated
    3. One of the locations are not valid (typo, anything else), it means that the time can **NOT** be calculated 
5. If everything works out :thumbsup:, and the estimated delivery time can be calculated, the result is in a JSON Object, the "time" key holds the value in milliseconds. If time can **NOT** be calculated, the value will contain a string telling you the error
6. To lunch the micro service, start the TimeGeneratorService (runs on localhost:60003), the run the Main.class

### Thank you for using our service! :heart_eyes_cat: