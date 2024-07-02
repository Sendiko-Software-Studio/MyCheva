# MyCheva Project Guidelines

Berikut adalah sedikit guide untuk struktur project dari MyCheva.

## Struktur Project

```
\app
    \core 
    \package_lainnya
```
<br>
Package core digunakan untuk menyimpan file file inti, yang digunakan di banyak package lainnya, seperti class Room Database, Retrofit, ViewModelFactory, dan lainnya.
<br><br>
Untuk setiap fitur, sudah tersedia package yang tertera. sehingga penempatan file file bisa terstruktur dengan rapi dan mudah untuk dibaca.

## Branching Git

Setiap fitur akan memiliki branch mereka masing-masing, sehingga dapat memaksimalkan efektifitas progress aplikasi. Setiap branch akan dikerjakan oleh satu orang, berfokus kepada 1 fitur. Mohon informasikan kepada developer lain jika anda mengubah file diluar package dari fitur yang anda kerjakan, Untuk meminimalisir terjadinya Conflict.

Jika fitur yang anda kerjakan dirasa cukup, buatlah Pull Request dari branch kalian ke branch main, dan mention ```@Sendiko``` sebagai reviewer untuk pengecekan Conflict. Setelah Pull Request di Merge, maka anda bisa mengerjakan fitur yang lainnya di branch yang telah ditentukan.

## Pesan Commit
Mohon gunakan pesan Commit yang singkat dan jelas tentang perubahan yang anda lakukan, sangat direkomendasikan untuk melakukan commit untuk file file yang memiliki hubugan satu sama lain(contoh: ```Screen, State, ViewModel```, pesan: Menyelesaikan CRUD) sehingga mudah untuk track perubahan file file.
<br><br>

### Semangat teman-teman!!
