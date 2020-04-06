#### Android Clean Architecture Sample with MVP design pattern.
A Sample app for implementing Clean Architecture with MVP and Repository pattern using RxJava.
I Also developed an mvvm sample with kotlin and power of coroutines. In this mvp sample I used Java and RxJava.
It is a simple app, gets list of cryptocurrency coins from coingecko api, save to database and show to user.

### Features
* Java
* Clean Architecture: Keep In mind, clean architecture starts from domain layer by implementing business logic(data models and abstract repository interfaces). so domain layer says to data layer what it should do and also domain layer provides proper data for UI layer.
* Repository Pattern: This pattern act on data layer. Repository Pattern Wraps around those data that provide by "BOTH network and Database". If your data provides only by network or only by database you can omit repository and do relavant tasks in UseCase.
* MVP Design Pattern: this design act on Presentation layer.
* View Binding(different from databinding): Instead of ButterKnife in classic mvp I use new Android ViewBinding.
* RxJava for concurrency and stream and also networking.
* Okhttp and Retrofit for networking.
* Room For Data Persistence. 
* Dagger2 for DI.
* Navigation Component for fragments.
* Dark Mode and Material Theme. 
* I used CoinGecko Free Api for coins list. https://www.coingecko.com/en
