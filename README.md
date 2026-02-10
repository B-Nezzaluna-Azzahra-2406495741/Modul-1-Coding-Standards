# eshop project

Nama: Nezzaluna Azzahra  
NPM: 2406495741  
Kelas: AdvPro - B

### Reflection 1

Dalam implementasi fitur Edit dan Delete pada program ini, saya telah menerapkan beberapa prinsip Clean Code untuk memastikan kode mudah dibaca dan di-maintenance. Saya menggunakan Meaningful Names seperti productId dan service.deleteById(id) agar tujuan dari setiap variabel dan metode langsung tersampaikan tanpa memerlukan komentar tambahan. Selain itu, dalam setiap fungsi dibuat agar hanya mengerjakan satu tugas spesifik, serta pemisahan tanggung jawab dengan membagi kode ke dalam lapisan Controller, Service, Repository, dan Model. Dalam Secure Coding, saya menerapkan pembuatan ID unik menggunakan UUID untuk menghindari manipulasi data melalui urutan ID yang mudah ditebak, serta menerapkan redirect setelah POST untuk mencegah pengiriman ulang data yang tidak disengaja oleh pengguna.

Namun, masih ada beberapa kekurangan yang perlu diperbaiki ke depannya. Salah satu kesalahan yang saya lakuakan adalah penggunaan metode GET untuk proses penghapusan produk, yang secara standar keamanan kurang baik karena operasi yang mengubah status data (state-changing) sebaiknya menggunakan metode POST atau DELETE untuk menghindari serangan CSRF atau eksekusi yang tidak disengaja melalui URL browser. Selain itu, program ini masih memerlukan validasi input yang lebih kuat, seperti memastikan kuantitas produk tidak bernilai negatif dan menangani skenario di mana produk tidak ditemukan dengan pesan error yang lebih informatif daripada sekadar melakukan redirect.

### Reflection 2

Setelah membuat unit test, saya merasa lebih yakin bahwa logika dasar program saya berjalan sesuai harapan/ekspektasi awal karena setiap komponen dan fungsi telah di-verify. Dalam sebuah class, jumlah unit test yang dibuat mencakup seluruh skenario yang ada, baik skenario positif (data valid) maupun skenario negatif (data tidak valid/error). Untuk memastikan pengujian sudah cukup, saya melihat persentase code coverage yang menunjukkan persentase baris kode yang dieksekusi oleh tes.

Mengenai pembuatan functional test suite baru yang memiliki prosedur setup dan variabel instance yang sama dengan tes sebelumnya, hal tersebut akan menurunkan kualitas kode karena melanggar prinsip Don't Repeat Yourself (DRY). Masalah clean code yang muncul adalah adanya duplikasi kode yang membuat kode menjadi sulit di-maintanance. Jika ada perubahan pada konfigurasi setup, kita harus mengubahnya di banyak tempat secara manual. Untuk memperbaiki hal ini, menurut saya perlu ada implementasi prinsip inheritance dengan membuat sebuah class base yang menampung semua prosedur setup dan variabel umum, sehingga kelas tes lainnya cukup melakukan extends pada kelas tersebut untuk menjaga kode tetap bersih dan efisien.
