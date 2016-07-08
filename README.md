
# RebDroid

Realm.io database browser

> Join in chat > [Slack](https://progcomrapadura.slack.com/)

<a href="http://www.methodscount.com/?lib=com.github.Snakeice%3ARebDroid%3A%2B"><img src="https://img.shields.io/badge/Methods and size-core: 312 | deps: 24381 | 85 KB-e91e63.svg"/></a><br>
[![Platform](http://img.shields.io/badge/platform-android-brightgreen.svg?style=flat)](http://developer.android.com/index.html)
[![Release](https://jitpack.io/v/Snakeice/RebDroid.svg)](https://jitpack.io/#Snakeice/RebDroid)[![Codacy Badge](https://api.codacy.com/project/badge/Grade/2b2f1f19d26c4c3bb0ee25384db602e4)](https://www.codacy.com/app/ProgComRapadura/RebDroid?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Snakeice/RebDroid&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/Snakeice/RebDroid.svg?branch=master)](https://travis-ci.org/Snakeice/RebDroid)<br>
[![Language](http://img.shields.io/badge/language-java-orange.svg?style=flat)](http://www.oracle.com/technetwork/java/javase/downloads/index.html) [![License](http://img.shields.io/badge/license-apache2.0-lightgrey.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-RebDroid-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/3804)
### Installation

Add it in your root build.gradle at the end of repositories:

```groovy	
allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```
Step 2. Add the dependency
```groovy
	dependencies {
	    compile 'com.github.Snakeice:RebDroid:v1.2'
	}
```
### How to use

Only call
```java
public void callDBBrowser() {
   DBViewer.start(context, realm.getRealmConfig());
}

```

### Todos

 * [x] <s>Disponibly to use in Gradle</s>
 * [ ] Write Tests
 * [ ] Add Code Comments
 * [ ] Add Night Mode
 * [ ] Add Typed edit
 * [ ] Add file browser
 * [ ] Make independent app

License
----

Apache 2.0

**Free Software, Hell Yeah!**
