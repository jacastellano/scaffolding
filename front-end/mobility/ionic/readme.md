
# Prerequisites

* Node.js
    - install LTS version
    - v10.15.1

* npm
    - 6.7.0

* ionic
    ```
    $ npm install -g ionic
    ```
    - ionic@4.10.2

* cordova
    ```
    $ npm install -g cordova
    ```
    - cordova@8.1.2


# Android Setup
Follow the official  [Android Setup Guide](https://ionicframework.com/docs/installation/android) in order to configure a virtual or physical device.

# Steps to build it

1. Install the npm packages described in the ./package.json

    ```
    $ npm install
    ```

2. Generate the native project

    ```
    $ ionic cordova prepare android
    ```

3. Start a virtual or physical device

4. Running with Cordova
    ```
    $ ionic cordova run android -l
    ```






