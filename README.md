# Sotrydniki
Проект для хранение данных о сотрудниках с их фотографией

## Начало работы

Для работы с проектом необходимо скопировать данную ссылку https://github.com/Nastakim951230/Sotrydniki, после чего запустить в программе **Android Studio** 

### Необходимые условия

Для запуска и естественной работы данного проекта необходим компьютер на базе системы не ниже *Windows 10* или даже лучше, а также интегрированная среда разработки для работы с платформой Android - *Android Studio*



### Установка

**Шаги установки:**

1. Скопировать ссылку на проект: https://github.com/Nastakim951230/Sotrydniki
2. Запустить программу Android Studio
3. После запуска нажмите на кнопки которые представленны на фотографии

![кнопки](https://sun9-west.userapi.com/sun9-11/s/v1/ig2/iTlglam0qA1GjRQvgnzilN35Io_3rI7xeulkwOoAG6xxhkA-0-4B1_ra6z7iIc-NNjGJFTnAy0pItl-x9gsbulIM.jpg?size=623x117&quality=96&type=album)

4. Вставте ссылку в поле *URL*, а в поле *Directory* выберите папку в которой будет храниться проект

**Папка должна быть только на английском языке**

![](https://sun9-west.userapi.com/sun9-53/s/v1/ig2/aPgujFXTEn4JEqjmv7R8EU12bqB3AcZQpoO2zUHrXiTiu8Mdrf-_TFBeHA0DOYce6M7FvLrX1fEiJ1afXhM-zn8L.jpg?size=743x138&quality=96&type=album)


### Рекомандции по изменению программы
Можно реализовать сортировку по всем данным, а не только по фамилии. Также можно сделать возможность вывода данных при работе сортировки и поиска.

Данный код находится в окне **MainActivity**

```java
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Filter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFilter=findViewById(R.id.filter);
        spinnerFilter.setAdapter(adapter);


        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                String vubor=null;
                switch (position)
                {
                    case 0:
                    {
                        vubor="Select * From Sotrudnic";
                        SelectList(vubor);
                    }
                    break;
                    case 1:
                    {
                        vubor="Select * From Sotrudnic ORDER BY Surname";
                        SelectList(vubor);
                    }
                        break;
                    case 2:
                    {
                        vubor="Select * From Sotrudnic ORDER BY Surname DESC";
                        SelectList(vubor);
                    }
                    break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
```
---
## Авторы

* Автор **Трифонова Анастасия** - [Ссылка на GitHub](https://github.com/Nastakim951230)

 [Ссылка на проект](https://github.com/Nastakim951230/Sotrydniki) 
