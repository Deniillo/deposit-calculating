# Калькулятор времени увеличения вклада

Консольное приложение для расчета времени, необходимого для увеличения вклада в заданное количество раз.

## Описание

Программа принимает на вход следующие параметры:
- **Сумма вклада** (в рублях).
- **Годовая процентная ставка** (в процентах).
- **Кратность увеличения вклада**.

На выходе программа выводит количество лет, необходимое для увеличения вклада до заданной суммы. Результат округляется до целого числа.

Дополнительно также был настроен CI Github Actions для автоматической проверки тестов приложения

## Требования

- Установленный [Docker](https://docs.docker.com/get-started/get-docker/).

## Запуск приложения

1. Клонируйте репозиторий:
   ```bash
   git clone https://github.com/Deniillo/deposit-calculating.git
   cd deposit-calculating
   ```
2. Запустите приложение с помощью Docker Compose:
    ```bash
   docker-compose build --no-cache
   docker-compose run --remove-orphans --service-ports app
   ```
   После запуска вы увидите приветственное сообщение:
    ```
    Привет! Ты в главном меню моей программы.
    Напиши --help для помощи или --quit для выхода из программы.
    ```
3. Пример использования(Ввод команды):
    ```bash
   calculate-payback -s 1000 -p 5 -m 2
   ```
    Вывод в консоль:
    ```
   Сумма вклада увеличится в 2.00 раза через 15 лет.
    ```
   
4. EXTRA:
   Если у вас по каким-то причинам не получается с помощью докера запустить, есть альтернативный вариант(java >21 нужна)...
   ```bash
   java -jar temp-files/deposit-calculating-ui.jar
   ```