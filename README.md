# AndroidTopToast
Library allows to show top toast (like Snackbar view).

### Screenshots
---

| Simple toast  | Custom colors   | Bottom gravity|
| :------------: |:---------------:| :-------------:|
|          <img align="left" width="150" height="300" src="https://github.com/TooLazyy/AndroidTopToast/blob/master/screenshots/toast6.jpg">     | <img align="left" width="150" height="300" src="https://github.com/TooLazyy/AndroidTopToast/blob/master/screenshots/toast5.jpg"> | <img align="left" width="150" height="300" src="https://github.com/TooLazyy/AndroidTopToast/blob/master/screenshots/toast4.jpg"> 		  |



| Above status bar  | Custom view     | Custom layoutId|
| :------------:     |:---------------:| :-------------: |
|          <img align="left" width="150" height="300" src="https://github.com/TooLazyy/AndroidTopToast/blob/master/screenshots/toast3.jpg">         | <img align="left" width="150" height="300" src="https://github.com/TooLazyy/AndroidTopToast/blob/master/screenshots/toast2.jpg"> | <img align="left" width="150" height="300" src="https://github.com/TooLazyy/AndroidTopToast/blob/master/screenshots/toast1.jpg"> 		   |

### Usage
---

Use `Kotlin DSL` to create `TopToast` instance:
```
val topToast {
            removeOnSwipe = TopToast.TOASTSWIPE.TOP
            context = this@MainActivity
            showAboveStatusBar = false
        }
```
To show toast use `topToast.showTopToas()`, to remove - `toast.removeTopToast()`


Full parameters:
```
val toast = topToast {
	    toastGravity = TopToast.TOASTGRAVITY.BOTTOM
            removeOnSwipe = TopToast.TOASTSWIPE.TOP
            context = this@MainActivity
            showAboveStatusBar = true
            viewSettings {
	    			duration = 3000L
	    			type = TOAST_TYPE.DEFAULT
				//gonna be ignored
				textColor = Color.RED
				//gonna be ignored
				backgroundColor = Color.BLACK
				//gonna be ignored
				textToShow = "text"
				//gonna be ignored
				layoutId = R.layout.my_awesome_toast
				//gonna be used (max priority)
                customView = custom
            }
        }
```

### Params

1. `duration` - duration of toast before autoremove, set to `TopToast.NO_AUTO_REMOVE` to disable autoremove
2. `toastGravity` - gravity on screen (`TopToast.TOASTGRAVITY.TOP` or `TopToast.TOASTGRAVITY.BOTTOM`)
3. `context` - activity context
4. `showAboveStatusBar` - should toast cover status bar or not (for **6+** devices toast **DOES NOT** cover status bar icons)
5. `removeOnSwipe` - swipe direction to remove toast from screen. `LEFT, TOP, BOTTOM, RIGHT` are supported. If `TopToast.TOASTSWIPE.NONE`(**default value**) selected - swipe not working, if `TopToast.TOASTSWIPE.ANY` selected - swipe detect any direction (left, top, bottom, right)
6. `viewSettings` - toast view settings
6.1  `textColor` - default text color (`WHITE`)
6.2 `backgroundColor` - default bg color (`RED`)
6.3 `textToShow` - text to show
6.4 `layoutId` - layout resource id.
6.5 `customView` - custom `View`.

### Changes
Now `viewSettings` contains parameter `type` of type `TOAS_TYPE`.
Possible values - `DEFAULT`, `CUSTOM_VIEW`, `CUSTOM_VIEW_ID`.
If `DEFAULT` is set - `layoutId` and `customView` gonna be **IGNORED**.
If `layoutId` or `customView` are set, specify a appropriate `type` (default value is `DEFAULT`).
