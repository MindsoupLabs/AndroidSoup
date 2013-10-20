AndroidSoup
===========

Android general purpose library. It current contains the following classes:

UniqueInstallationId
------------
This class provides a unique ID per installation. It is guaranteed to be persistent between device reboots, but not between app un-/reinstalls. It can simply be used as follows:
```java
String uuid = UniqueInstallationId.getId(this.getContext(), "MyAppName");
```