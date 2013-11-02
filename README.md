AndroidSoup
===========

Android general purpose library. It current contains the following classes:

UniqueInstallationId
------------
This class provides a unique ID per installation. It is guaranteed to be persistent between device reboots, but not between app un-/reinstalls. It can simply be used as follows:
```java
String uuid = UniqueInstallationId.getId(this.getContext(), "MyAppName");
```

ConnectivityCheck
------------
This class provides an asynchronous way of checking for a usable internet connection. The class implements runnable, which can be executed in a separate thread. The class uses a listener class - ConnectivityListener - as a callback when it has determined if there is an internet connection. Example:
```java
Class Example implements ConnectivityListener {

  public void startConnectivityCheck(Context myContext) {
    Thread check = new Thread(new ConnectivityCheck(this, myContext, "http://google.com"));
    check.start();
  }
  
  public void onConnectionCheckComplete(final boolean hasConnection) {
    if(hasConnection)
      System.out.println("we have a connection");
    else
      System.out.println("we do not have a connection");
  }
}
```

If the ConnectivityListener makes changes to an activity's UI, keep in mind that you should use Activity.runOnUiThread(Runnable) and execute your UI changes inside the runnable.
