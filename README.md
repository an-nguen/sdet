# SDET-практикум 2024

SDET stands for Software Developer Engineer in Test.

Данный репозиторий является проектом-решением для тестового задания, нужная для принятия в участии на практикум от компаний Simbirsoft.

Данный проект выполнен в IDE JetBrains Intellij IDEA, с использованием следующих сторонних пакетов:

- JUnit 5
- Selenium

## Кейс. Заполнение формы регистрации

Предусловие:

1. Открыть браузер
2. Перейти на страницу входа: https://demoqa.com/automation-practice-form

Шаги:

1. Заполнить поле First Name произвольной строкой
2. Заполнить поле Last Name произвольной строкой
3. Заполнить поле Email строкой формата name@example.com
4. Выбрать любое значение в Gender с помощью переключателя
5. Заполнить поле Mobile произвольными 10 цифрами
6. Заполнить поле Date of birth произвольной датой с помощью всплывающего календаря
7. Заполнить поле Subjects произвольной строкой
8. Загрузить любое изображение в поле Picture
9. Заполнить поле Current Address произвольной строкой
10. Выбрать любое значение в Select State с помощью выпадающего списка
11. Выбрать любое значение в Select City с помощью выпадающего списка
12. Нажать кнопку Submit

Ожидаемый результат:

1. Появилось всплывающее окно с заголовком Thanks for submitting the form
2. В таблице на окне отображаются введенные ранее значения

# Useful Resources

- [JUnit User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Selenium Docs Overview](https://www.selenium.dev/documentation/overview/)
- [Page Object Model](https://www.browserstack.com/guide/page-object-model-in-selenium)
- [Selenium File Upload](https://www.selenium.dev/documentation/webdriver/elements/file_upload/)