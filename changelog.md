FEATURES :
+ Add command /cw
    + /cw raincontrol on|off 
        + Permission : cryweather.rainControl
    + /cw day (world) 
        + Permission : cryweather.day cryweather.day.otherworld
    + /cw night (world)
        + Permission : cryweather.night cryweather.night.otherworld
    + /cw morning (world) 
        + Permission : cryweather.morning cryweather.morning.otherworld
    + /cw afternoon (world)
         + Permission : cryweather.afternoon cryweather.afternoon.otherworld 
    + /cw evening (world) 
        + Permission : cryweather.evening cryweather.evening.otherworld 
    + /cw sun (world) (Duration) 
         + Permission : cryweather.sun cryweather.sun.otherworld 
    + /cw rain (world) (Duration)
         + Permission : cryweather.rain cryweather.rain.otherworld 
    + /cw set <world> (Duration) 
        + Permission : cryweather.set
    + /cw worldspeed (speed) 
        + Permission : cryweather.worldspeed
+ Add autocompletion

CHANGES :
+ Removed permission cryweather.chelp
+ Removed permission cryweather.cenablerain
+ Removed permission cryweather.cdisablerain
+ Removed permission cryweather.wcday
+ Removed permission cryweather.cday
+ Removed permission cryweather.wnight
+ Removed permission cryweather.cnight
+ Removed permission cryweather.cmorning
+ Removed permission cryweather.cafternoon
+ Removed permission cryweather.cevening
+ Removed permission cryweather.csun
+ Removed permission cryweather.wcsun
+ Removed permission cryweather.crain
+ Removed permission cryweather.wcrain
+ Removed permission cryweather.cset
+ Removed command cweather
+ Removed command chelp
+ Removed command cenablerain
+ Removed command cday
+ Removed command wcday
+ Removed command cnight
+ Removed command wcnight
+ Removed command cmorning
+ Removed command cafternoon
+ Removed command cevening
+ Removed command csun
+ Removed command wcsun
+ Removed command crain
+ Removed command wcrain
+ Removed command cset
+ Removed command cworldspeed
+ Removed useless logs on console
+ Updated config file
+ Updated saving system
+ Worldspeed in now apply on all worlds

BUGFIX :
+ /cworldspeed 0 was adding 1 day per ticks
