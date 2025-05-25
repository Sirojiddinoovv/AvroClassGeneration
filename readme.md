Параметры setSource(...) и setOutputDir(...) больше не используются напрямую в новых версиях плагина com.github.davidmc24.gradle.plugin.avro (начиная с версии 1.2.x и выше). Вместо них плагин автоматически ищет .avsc и .avdl файлы по умолчанию в папке:
src/main/avro

А сгенерированные .java классы складывает в:
build/generated-main-avro-java

setCreateSetters(true) — генерирует setXxx(...) методы для каждого поля.

setFieldVisibility("private") — задаёт видимость полей в сгенерированных классах:

"PUBLIC" — поля будут public.

"PUBLIC_DEPRECATED" — поля public с аннотацией @Deprecated.

"PRIVATE" — поля будут private, доступ через getter/setter.

setOutputCharacterEncoding(Charsets.UTF_8) — устанавливает кодировку для сгенерированных файлов.

setStringType("String") — определяет тип для строковых полей ("String" или "CharSequence").

setEnableDecimalLogicalType(true) — включает поддержку логического типа decimal.

пример в java class:
public class User extends SpecificRecordBase {
private long id;
private String fullName;
private UserTypeEnum type;
private Address address;
private ContactInfo contactInfo;
private List<Document> documents;
}