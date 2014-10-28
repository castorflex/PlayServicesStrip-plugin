# Description

Strip your play services to make it smaller!
This plugin is heavily based on [this gist][gist], so all credits go to [@dextorer].

# Usage

The plugin is available on Maven Central  [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.castorflex.playservicesstrip/plugin/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/com.github.castorflex.playservicesstrip/plugin)

Add the plugin to your classpath, apply the plugin (after the android gradle plugin) and add `includes` OR `excludes`

```groovy
dependencies {
	classpath 'com.github.castorflex.playservicesstrip:plugin:1.0.1'
}

apply plugin: 'com.android.application'
apply plugin: 'playservicesstrip'

playservices {
    includes = ["ads", "maps", "common", "internal"]
    //OR
    excludes = ["drive", "wearable"]
}
```

Here are the supported packages:

```
ads
analytics
actions
appindexing
appstate
auth
cast
common
drive
dynamic
fitness
games
gcm
identity
internal
location
maps
panorama
plus
security
tagmanager
wallet
wearable
```


[gist]: https://gist.github.com/dextorer/a32cad7819b7f272239b
[@dextorer]: https://github.com/dextorer
