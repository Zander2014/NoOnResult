# NoOnResult
对onActivityResult封装和使用，无需重写onActivityResult，以回调的方式拿到startActivityForResult的结果，解决了以往跳转页面和处理结果分离的痛点。
# Setup

add to dependencies

```groovy
dependencies {
        implementation'com.github.AnotherJack:AvoidOnResult:1.0.0'
}
```

# Usage

callback方式

```java
new NoOnResult(activity).startForResult(XXActivity.class, new NoOnResult.Callback() {
         @Override
         public void onActivityResult(int resultCode, Intent data) {
             if (resultCode == Activity.RESULT_OK){
                 // get your data from intent
                 
             }else {
                 
             }
         }
     });
```

