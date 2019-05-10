# Delivz-MVVM

This Android app is developed using Kotlin language,
following recomended architecture explained at: https://developer.android.com/jetpack/docs/guide

Similar to this diagram:

![Architecture Components Diagram by Android developers](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)


[Android Paging Library](https://developer.android.com/topic/libraries/architecture/paging/#support-different-data-arch) is integrated to lazy load data by pages. Paging library work by coordinating the interactions between components as explained in this diagram:

<img src="https://codelabs.developers.google.com/codelabs/android-paging/img/511a702ae4af43cd.png" alt="Paging Library Components Diagram"/>


### Package Structure:
- **.data:** manipulates data
  - **.db:** implements database operations
  - **.dto:** Data objects and entites
  - **.paging:** Load data from API using [Pagination]()
- **.di:** provides dependencies
- **.view:** displays data (Activities, View holder and Adapter)
  - **.adapter:** implements View holder and Adapter for recyclerview
- **.viewmodel:** View model


### Libraries
* [Android Support Library][support-lib]
* [Android Data Binding][data-binding]
* [Android Architecture Components][arch]
  * [Paging][paging]
  * [Room][room]
  * [Live Data][live-data]
  * [View Model][view-model]
* [RxJava][rxjava]
* [Dagger 2][dagger2] for dependency injection
* [Retrofit][retrofit] for REST api communication
* [Picasso][picasso] for image loading
* [Google Maps][map]


### Result:
<img src="https://github.com/themhsami/Delivz-MVVM/blob/master/Screenshot_1537595887.png" alt="screenshot 1" align="left" height="500"/>
<img src="https://github.com/themhsami/Delivz-MVVM/blob/master/Screenshot_1537597762.png" alt="screenshot 2" align="left" height="500"/>

[support-lib]: https://developer.android.com/topic/libraries/support-library/index.html
[arch]: https://developer.android.com/arch
[paging]: https://developer.android.com/topic/libraries/architecture/paging/
[room]: https://developer.android.com/topic/libraries/architecture/room
[live-data]: https://developer.android.com/topic/libraries/architecture/livedata
[view-model]: https://developer.android.com/topic/libraries/architecture/viewmodel
[data-binding]: https://developer.android.com/topic/libraries/data-binding/index.html
[dagger2]: https://google.github.io/dagger
[retrofit]: http://square.github.io/retrofit
[rxjava]: https://github.com/ReactiveX/RxJava
[picasso]: http://square.github.io/picasso/
[map]: https://developers.google.com/maps/documentation/android-sdk/intro
