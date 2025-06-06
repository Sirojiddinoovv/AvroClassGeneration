
# AvroClassGenerator

Проект на Spring Boot и Kotlin, предназначенный для генерации Java-классов из Avro-схем и работы со Schema Registry.

---

## 📦 Структура проекта

- `src/main/avro/` — директория с Avro-схемами (`*.avsc`)
- `build/generated-main-avro-java/` — путь, куда Gradle плагин сгенерирует Java-классы
- `src/main/kotlin/` — основная бизнес-логика приложения
- `src/test/kotlin/` — модульные тесты

---

## ⚙️ Используемые технологии

- Spring Boot
- Kotlin
- Gradle
- Avro
- Confluent Schema Registry
- Gradle Plugin: `com.github.davidmc24.gradle.plugin.avro`

---

## 🛠️ Генерация Avro-классов

> Плагин автоматически сканирует `src/main/avro` и генерирует `.java` классы в `build/generated-main-avro-java`.

### Шаги:

```bash
./gradlew clean build
```

### Пример конфигурации:

```kotlin
avro {
    setCreateSetters(true)
    setFieldVisibility("PRIVATE")
    setOutputCharacterEncoding(Charsets.UTF_8)
    setStringType("String")
    setEnableDecimalLogicalType(true)
}
```

---

## 🔁 Работа со Schema Registry

Для загрузки и загрузки Avro-схем используйте следующие утилиты от Confluent:

### 🔽 Импорт схемы:

```bash
curl -X POST http://localhost:8081/subjects/User-value/versions \
     -H "Content-Type: application/vnd.schemaregistry.v1+json" \
     -d @src/main/avro/User.avsc
```

### 🔼 Получение схемы:

```bash
curl http://localhost:8081/subjects/User-value/versions/latest | jq
```

---

## 📄 Полезные параметры Avro-плагина (из старого README)

- `setCreateSetters(true)` — генерирует `setXxx(...)` методы.
- `setFieldVisibility("PRIVATE")` — поля становятся приватными, используются геттеры/сеттеры.
- `setOutputCharacterEncoding(Charsets.UTF_8)` — кодировка.
- `setStringType("String")` — тип строк: `"String"` или `"CharSequence"`.
- `setEnableDecimalLogicalType(true)` — поддержка `decimal`.

---

## 🧪 Тестирование

```bash
./gradlew test
```

---

## 🧼 Очистка и пересборка

```bash
./gradlew clean build
```

---

## 📂 Пример Avro-схемы

`src/main/avro/User.avsc`:

```json
{
  "type": "record",
  "name": "User",
  "namespace": "uz.nodir.avro",
  "fields": [
    { "name": "name", "type": "string" },
    { "name": "age", "type": "int" }
  ]
}
```

---

## ℹ️ Заметки

- Schema Registry по умолчанию доступен на `http://localhost:8081`
- Используйте `jq` для форматирования JSON-ответов (`brew install jq` или `apt install jq`)

---

## 👤 Автор

Нодирбек — Kotlin/Java разработчик с опытом генерации Avro-классов и интеграции с Kafka/Schema Registry.
