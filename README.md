#Testing Craft Demonstration Challenge

-------------------------------------------------------------------------------

**Getting Started**

Unzip the project files. Open the project in the editor of choice. Note: IntelliJ IDEA was used to develop these test cases. If you do not have IntelliJ IDEA and want to use a free version or try the ultimate version they both can can be downloaded from
[this link](https://www.jetbrains.com/idea/download/).

For portability, a sample helper class: **HelperMethods.java**  was created within the folder: **\src\main\java**.

For simplicity, all tests are located within the folder:**\src\test\java**.

**Dependencies**

Several different dependencies are used and managed using Maven. Feel free to view these dependencies within the POM file.

-------------------------------------------------------------------------------
```
This project contains several basic test cases to validate the following:
```

- [x] Functional testing 
- [x] Security testing
- [x] Load Testing
- [x] Web UI Testing
- [x] Unit Testing 
 
```
This test cases follow a Behavior-driven development pattern.
```

*  (Given) some context
*  (When) some action is carried out
*  (Then) a particular set of observable consequences should obtain

```
Test Cases
```
* TC1... helps ensure that users can ONLY access the API with a valid key.
* TC2... helps ensure that users can access the API with a valid key. 
* TC3... helps ensure that users enter valid query parameter to access API with a valid key.
* TC4... helps ensure that we get JSON... don't want XML if expecting JSON
* TC5... helps ensure that we get default count of 10 when no limit param provided
* TC6... helps ensure that we get same count of 25 when limit param of 25 is provided
* TC7... helps ensure that users can NOT exceed any API call limits //Note: we use the DEMO_KEY param since it's limited to 40 calls/day.

-------------------------------------------------------------------------------

**HTTP REQUEST**
```
GET https://api.nasa.gov/planetary/sounds
```

**QUERY PARAMETERS**
| Parameter | Type | Default | Description |
|:------------:|:------------:|:------------:|:------------:|
| q     |      string |    None    |     Search text to filter results |
| limit     |      int |    10   |      number of tracks to return |
| api_key     |      string |    DEMO_KEY    |  api.nasa.gov key for expanded usage |

**Example Query**

[https://api.nasa.gov/planetary/sounds?q=apollo&api_key=DEMO_KEY](https://api.nasa.gov/planetary/sounds?q=apollo&api_key=DEMO_KEY)

-------------------------------------------------------------------------------

Email: MCharlesJames@gmail.com as needed.
