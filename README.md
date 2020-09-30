# News-API
News API is an application that fetch news from [https://newsapi.org](https://newsapi.org). To run this application you need to [Register here](https://newsapi.org/register) and get 32 digit API key.

## Features
- Show Top-Headlines
- View Single News in detail
- Handled Internet connectivity
- Used Data models

## Requirement
- Android 5.1 or later (Minimum SDK level 22)
- Android Studio (to compile and use)

## Library Used
```gradle
implementation 'com.squareup.retrofit2:retrofit:2.5.0'
implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
implementation 'com.squareup.picasso:picasso:2.5.2'
```
#### String.xml
```xml
<resources>
    <string name="api_key">Your API key</string>
</resources>
```


## License
Licensed under the Apache License, Version 2.0 (the "License");
You may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0
