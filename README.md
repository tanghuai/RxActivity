# RxActivity
A more easier way to use startActivityForResult with RxJava 3.1.0

## here is the demo
```
RxActivity.with(this)
          .addIntent(Intent().apply {
                putExtra("param", "abc")
          })
          .startActivity(SecondActivity::class.java)
          .subscribe {
              Snackbar.make(window.decorView, "Received Result:$it", Snackbar.LENGTH_SHORT).show()
          }
```
## How to use

Gradle:
```

allprojects {
          repositories {
	          ...
		maven { url 'https://jitpack.io' }
	}
}

dependencies {
      implementation 'com.github.tanghuai:RxActivity:1.0.0'
}

```

