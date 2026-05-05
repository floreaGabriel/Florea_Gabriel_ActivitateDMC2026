# Laborator 5

## Pe scurt (ce reții pentru examen)

- **Model (clasă)**: + **Date** / timp; tot **enum** + tipuri de bază ca la L4.
- **Listă obiecte** în memorie: `ArrayList<Model>`.
- **ListView** + **`ArrayAdapter`**: constructor cu context, `layout.simple_list_item_1` sau layout propriu, listă; afișare prin `toString()` a modelului.
- **Item click** → ex. `Toast` cu obiectul.
- **Long click** șterge din `ArrayList` + `adapter.notifyDataSetChanged()` (sau ștergi din listă și reafișezi).

**În proiect:** s-a trecut la **adapter custom** `MagazinAdapter` (L6) — pentru examen, ideea de **ListView** + listă e în `ActivitateLab4` + `GFMagazin.toString()`.

**Rădăcină proiect:** `laborator2/app/src/main/`

---

## Cerințe (original)

1. Folositi aplicatia pe care o aveti deja sau creați o noua aplicație în Android Studio cu o activitate Empty Views, în java, cu API 23/24.

2. Modificati clasa pe care o aveti pentru un obiect din realitate care conține inițialele numelui vostru. Aveti grija ca obiectul sa fie real. Exemplu Alin Zamfiroiu – clasa Magazin. Această clasă trebuie să conțină minim cinci atribute de tipuri diferite, dintre care minim unul să fie string, unul de tipul boolean, unul de tip întreg, un atribut sa aibă valori posibile într-o mulțime finită (enum). Puteți să folosiți și clasa din laboratorul precedent. Mai adaugati un atribut de tip Date sau Time.

3. Trebuie sa modificati si activitatea de adaugare sau creare obiect. Datele introduse de utilizator sunt folosite pentru crearea unei instanțe a clasei. Această instanță este returnată către prima activitate, din cadrul căreia a fost deschisă activitatea de introducere date. Trtebuie sa folosii un Intent dependent. Obiectul este transmis prin bundle (poate fi folosit si Parcelable sau trimiteți fiecare atribut in parte). În activitatea principala acest obiect îl adăugați într-o listă de obiecte.

5. Folosiți un ListView pentru afișarea tuturor obiectelor din listă. În clasa creată implementați metoda toString, iar în ListView afișați obiectele utilizând această metodă. Utilizati ArrayAdapter pentru afisare ain ListView.

6. Atunci când utilizatorul selectează din ListView un obiect, acesta este afișat prin intermediul unui Toast.

7. Pentru evenimentul de LongItemClick pe un obiect din lista, acesta este șters din ListView dar și din lista de obiecte.

---

## Index rapid (exam) — „mă duc direct la …”

| Cauți / te întreabă despre… | Deschide (în `laborator2/`) |
|----------------------------|----------------------------|
| **ListView**, listă, `ArrayAdapter` (variantă L5) / adapter custom (actual) | `app/src/main/java/.../ActivitateLab4.java` (`ListView`, `setAdapter`, `MagazinAdapter`) |
| Adăugare obiect din formular, `onActivityResult` | `ActivitateLab4.java` + `PrelucrareDateLab4Activity.java` |
| `toString()` pentru afișare | `.../GFMagazin.java` |
| Rând listă (layout o linie) | `res/layout/item_magazin.xml` (L6) |
| Item click, long click (în proiect: long = favorite L7) | `ActivitateLab4.java` (`setOnItemClickListener`, `setOnItemLongClickListener`) |
| Câmp **Date** | `GFMagazin.java` + `PrelucrareDateLab4Activity` (`CalendarView` / dată) |

**Notă:** L5 cere explicit `ArrayAdapter` simplu — la tine e `MagazinAdapter` care **extinde** `ArrayAdapter<GFMagazin>`: aceeași idee, cu layout propriu (L6).

---

## Mapare cerințe → fișiere

| # | Subiect | Unde e în proiect |
|---|---------|--------------------|
| 1–2 | Model + dată | `GFMagazin.java` |
| 3 | Listă, primire obiect, adăugare | `ActivitateLab4.java` (`REQUEST_ADD`, `onActivityResult`, `magazine.add`) |
| 5 | ListView, adapter, `toString` | `ActivitateLab4.java` + `GFMagazin.java` + `MagazinAdapter.java` |
| 6 | Click pe item | `ActivitateLab4.java` (`setOnItemClickListener`) |
| 7 | Long click (L5 = ștergere) | În cerință: ștergere; în proiect actual long click = `salveazaFavoritInFisier` (L7) |

---

## Pași (rezolvare)

1. `ArrayList<Magazin>` în activitatea principală.
2. `ListView` + `new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista)` (sau listă de stringuri din `toString` — `adapter` custom dacă vrei rând complex).
3. `startActivityForResult` (formular) → `onActivityResult` → `lista.add(obiect)` → `notifyDataSetChanged()`.
4. `setOnItemClickListener` → `Toast` cu `lista.get(position).toString()`.
5. `setOnItemLongClickListener` → `remove(position)` + `notifyDataSetChanged()`.
