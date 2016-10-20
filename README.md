MaterialDialog
==============

[![MD-0.0.1](https://jitpack.io/v/iballan/materialdialog.svg)](https://jitpack.io/#iballan/materialdialog)

## Material Dialog (MD) allows you to implement material dialogs in the simplest way

Screenshots:
--------

![Screenshots/screenshot1](https://raw.githubusercontent.com/iballan/materialdialog/master/screenshots/md.gif)


Usage :

Java:
``` java
	new MD.Builder(this) // Context or Activity or ApplicationContext
		.title("This is Title") // String or resource
		.message("This is Message") // String or resource
		.positiveText("OK") // String or resource
		.build().show(); // SHOW it!
```

Install
--------

You can install using Gradle:

```gradle
	repositories {
	    maven { url "https://jitpack.io" }
	}
	dependencies {
	    compile 'com.github.iballan:MaterialDialog:0.0.2'
	}
```

Contact me:
--------

Twitter: [@mbh01t](https://twitter.com/mbh01t)

Github: [iballan](https://github.com/iballan)

Website: [www.mbh01.com](http://mbh01.com)

Credits:
--------

-

License
--------

    Copyright 2016 Mohamad Ballan.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.