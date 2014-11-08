package com.castorflex.playservicesstrip.plugin

import static com.castorflex.playservicesstrip.plugin.Utils.isEmpty

class PlayServicesStripExtension {

    String[] includes = []
    String[] excludes = []
    boolean shouldStrip = true;

    boolean getHasConfig() {
        includes.length > 0 || excludes.length > 0
    }

    public void checkConfig() {
        if(isEmpty(includes) && isEmpty(excludes)){
            throw new IllegalArgumentException("Invalid playservices config: includes and excludes empty")
        }

        if(!isEmpty(includes) && !isEmpty(excludes)){
            throw new IllegalArgumentException("Invalid playservices config: you cannot have both includes AND excludes")
        }

        includes.each { String s ->
            if(!PlayServicesPackages.PACKAGES.containsKey(s))
                throw new IllegalArgumentException("Unknown playservices package $s")
        }

        excludes.each { String s ->
            if(!PlayServicesPackages.PACKAGES.containsKey(s))
                throw new IllegalArgumentException("Unknown playservices package $s")
        }
    }
}