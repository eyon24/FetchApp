# Name - Fetch Application

### Author: Eric Yon

## Description

App that fetches data from https://fetch-hiring.s3.amazonaws.com/hiring.json
and displays it. The data is sorted by listId then by name, and all items with
an empty name are removed. The data is fetched using RetroFit and displayed on a RecyclerView.
The app is scrollable so the user can access each item that is passed to the view.


## Installation

Import the project into Android Studio and run the application on a device
or
install directly on android device from app-debug.apk

## Notes

I had trouble testing the UI. I looked up the error it was throwing, and it seemed to be
something to do with the permissions. I was unable to fix this.
