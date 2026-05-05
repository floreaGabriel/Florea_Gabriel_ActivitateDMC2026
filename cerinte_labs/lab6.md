# Laborator 6

## Pe scurt (ce reții pentru examen)

- **Adapter personalizat**: clasa `extends ArrayAdapter<Model>`; override `getView` — `LayoutInflater` inflă `item_….xml` și setezi `TextView` din obiect.
- **Editare** același ecran de formular: `Intent` cu `putExtra` obiectul selectat + index poziție; la salvare: **înlocuiești** `lista.set(i, obiect)` (nu adaugi dublu).
- **Parcelable**: model `implements Parcelable` — `writeToParcel`, constructor din `Parcel`, `CREATOR`; în `Intent`: `putExtra("k", obiect)` / `getParcelableExtra`.

**Rădăcină proiect:** `laborator2/app/src/main/`

---

## Cerințe (original)

1. Utilizați proiectul din laboratorul precedent.

2. Modificați modul de afișare a obiectelor în ListView utilizând un Adapter personalizat. Modul de prezentare este la alegerea voastră.

3. Modificați funcționalitatea metodei abonată la evenimentul de ItemClick, astfel încât să se deschidă activitatea de completare date pentru modificarea obiectului selectat. La deschiderea activității câmpurile sunt completate cu informațiile primite de la obiectul selectat, iar la salvare vor fi modificate în acel obiect. Mare atenție: nu adăugați un alt obiect ci îl modificați pe cel selectat.

4. Pentru trimiterea obiectelor intre activitati trebuie sa folositi Parcelable.

---

## Index rapid (exam) — „mă duc direct la …”

| Cauți / te întreabă despre… | Deschide (în `laborator2/`) |
|----------------------------|----------------------------|
| `Parcelable`, `CREATOR`, `writeToParcel` | `app/src/main/java/.../GFMagazin.java` |
| **Adapter** custom, `getView`, `LayoutInflater` | `.../MagazinAdapter.java` |
| Rând listă (XML) | `res/layout/item_magazin.xml` |
| Click → `Intent` + `putExtra("magazin", obiect)` + poziție | `app/src/main/java/.../ActivitateLab4.java` |
| Formular: primești `Parcel`, completezi, trimiți înapoi | `.../PrelucrareDateLab4Activity.java` (`getParcelableExtra`, `completeazaCampuri`, `setResult`) |
| înlocuire în listă: `set(position, obiect)` | `ActivitateLab4.java` (`REQUEST_EDIT`, `onActivityResult`) |

---

## Mapare cerințe → fișiere

| # | Subiect | Unde e în proiect |
|---|---------|--------------------|
| 2 | `MagazinAdapter` + `item_magazin.xml` | `MagazinAdapter.java`, `res/layout/item_magazin.xml` |
| 3 | Deschidere edit, prefill, update listă | `ActivitateLab4.java`, `PrelucrareDateLab4Activity.java` |
| 4 | `Parcelable` | `GFMagazin.java`; extras în ambele activități de mai sus |

---

## Pași (rezolvare)

1. Model: `implements Parcelable` (sau generare + adaptare câmpuri, inclusiv `Date` ca `long` în `Parcel`).
2. Layout rând: `item_….xml` — id-uri clare.  
3. `class X extends ArrayAdapter<Model>` { `getView` → inflate, bind date }.  
4. `ListView` primește `new X(this, R.layout.item_…, list)`.  
5. Pe click: `putExtra(POZITIE, i)` + `putExtra(OBIECT, item)`; formular: dacă e edit, `setResult` + în principal `lista.set(poz, nou)` + `notifyDataSetChanged()`.
