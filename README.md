# wagproject

In real life scenario, the repo would not have all the unnecessary files (there are two commits that are named unnecessary files) in the project description it said the project must run as is, so I just uploaded everything in the android project dir.  
  
In the java classes, I hardcoded the TAG strings becuase once proguard obfuscates the code it will be impossible to read prod logs without the tags.  
  
I added all the other values in the StackExchangeItem model because I was planning on making a detailed item page but I decided not to because I already spent about 3 hours on the list.  
  
The app assumes that the JSONObject value "Account_id" is a uuid for all the users and unique.  
  
No real thoughtful meaning in the target / min sdk so disregard.  
  
External libs : Volley for network requests, Picasso for image loading.   
Used Volley because it handles network cases well and makes the code more readable and concise.  
Used Picasso (instead of just using Volley for image loading too) because I'm used to using Picasso no real reason in preferring it over Glide or Volley

