# RxActivity
A more easier way to use startActivityForResult

here is the demo
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

