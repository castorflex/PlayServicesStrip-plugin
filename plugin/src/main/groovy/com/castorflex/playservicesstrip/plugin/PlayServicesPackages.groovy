package com.castorflex.playservicesstrip.plugin;

/**
 * Created by castorflex on 10/23/14.
 */
class PlayServicesPackages {

    static enum Package {
        ads(["com/google/ads/**", "com/google/android/gms/ads/**"]),
        analytics(["com/google/android/gms/analytics/**"]),
        actions(["com/google/android/gms/actions/**"]),
        appindexing(["com/google/android/gms/appindexing/**"]),
        appstate(["com/google/android/gms/appstate/**"]),
        auth(["com/google/android/gms/auth/**"]),
        cast(["com/google/android/gms/cast/**"]),
        common(["com/google/android/gms/common/**"]),
        drive(["com/google/android/gms/drive/**"]),
        dynamic(["com/google/android/gms/dynamic/**"]),
        fitness(["com/google/android/gms/fitness/**"]),
        games(["com/google/android/gms/games/**"]),
        gcm(["com/google/android/gms/gcm/**"]),
        identity(["com/google/android/gms/identity/**"]),
        internal(["com/google/android/gms/internal/**"]),
        location(["com/google/android/gms/location/**"]),
        maps(["com/google/android/gms/maps/**"]),
        panorama(["com/google/android/gms/panorama/**"]),
        plus(["com/google/android/gms/plus/**"]),
        security(["com/google/android/gms/security/**"]),
        tagmanager(["com/google/android/gms/tagmanager/**"]),
        wallet(["com/google/android/gms/wallet/**"]),
        wearable(["com/google/android/gms/wearable/**"]);

        final String[] packageNames;
        public Package (packagenames){
            this.packageNames = packagenames;
        }
    }

    static def PACKAGES = [
            ads: Package.ads,
            analytics: Package.analytics,
            actions: Package.actions,
            appindexing: Package.appindexing,
            appstate: Package.appstate,
            auth: Package.auth,
            cast: Package.cast,
            common: Package.common,
            drive: Package.drive,
            dynamic: Package.dynamic,
            fitness: Package.fitness,
            games: Package.games,
            gcm: Package.gcm,
            identity: Package.identity,
            internal: Package.internal,
            location: Package.location,
            maps: Package.maps,
            panorama: Package.panorama,
            plus: Package.plus,
            security: Package.security,
            tagmanager: Package.tagmanager,
            wallet: Package.wallet,
            wearable: Package.wearable
    ]
}
