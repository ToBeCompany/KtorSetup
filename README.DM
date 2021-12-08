1. Подключить зависимости:
в зависимости:
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2'
    implementation "io.ktor:ktor-client-core:1.6.6"
    implementation "io.ktor:ktor-client-cio:1.6.6"
    implementation "io.ktor:ktor-client-serialization:1.6.6"
в плагины сверху:
  id 'org.jetbrains.kotlin.plugin.serialization' version '1.5.31'

2. Создаем клиент:
    val client = HttpClient(CIO){
        install(JsonFeature){
            serializer = KotlinxSerializer()
        }

3. Создаем data classes:
    JSONtoKotlinDataClass
    ставим галочку на значениях по умолчанию (avoid null value)
    ADVANCED
    аннотацию переключаем на kotlinx.serialization

4. Создаем запрос:
    Обязательно в корутине с диспатчером IO
            GlobalScope.launch(Dispatchers.IO) {
                client.get<T>("https://jsonplaceholder.typicode.com/posts")
            }
5. Получаем результат:


Не забудьте указать
     <uses-permission android:name="android.permission.INTERNET" />
в манифесте


