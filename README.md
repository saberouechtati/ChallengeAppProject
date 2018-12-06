# ChallengeAppProject
Android challenge application project to retrieve a list of public repositories of a GitHub user using the Github API, and display them.

## Presentation: 
https://prezi.com/view/6tEBVW9R9tOrz5DL3ItN/



# Getting started
You can glone or download this project to your local machine.

# Dependencies

## Retrofit, GSON
'com.squareup.retrofit2:retrofit:2.5.0'  
'com.squareup.retrofit2:converter-gson:2.5.0'

## Room 
'android.arch.persistence.room:runtime:1.1.1'  
'android.arch.persistence.room:compiler:1.1.1'

## Lifecycle, ViewModel and LiveData
'android.arch.lifecycle:compile:r1.1.1'  
'android.arch.lifecycle:extensions:1.1.1'

## Development concept base on (Android Jetpack Components and Retrofit) 
We have 5 distinations:  
1. splash screen  
2. Master destination, for display GitHub user's repository list (using RecyclerView)  
3. Detail destination, for displaying the selected GitHub user's repository  
4. Search destination, for entering the GitHub username...
5. Login destination, for the login (the login should be optional)

We will have 1 single Activity and 5 Fragments to implement the 5 destinations. 
For the communicatiom between the destinations we will use a sharedViewModel in the Activity scope.

