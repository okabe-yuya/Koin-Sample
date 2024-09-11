🪙 Koin with Kotlin(Gradle)
===

DI用のフレームワーク[Koin](https://github.com/InsertKoinIO/koin)をシンプルな構成で動作させてみた。  
意外と`build.gradle.kts`で記述されたKoinのサンプルが見つけられず、手元で新しくプロジェクトを作成・設定して動かすところまでを用意することにハードルを感じたため、public repositoryとして成果物を公開しています。

## Usage

- シンプルな`module`を使った記述によるDI
- `Annotation`を使った記述によるDI
  - `@Single`と`@ComponentScan`の合わせ技

```kotlin
./gradlew run
```

