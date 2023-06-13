db = new Mongo().getDB('accommodation');

db.createCollection('accommodation');

db.accommodation.insert (
    {
        "hostId": null,
        "name": "nekiime",
        "address": {
            "streetNumber": "25",
            "streetName": "beka ulica",
            "postalCode": "25",
            "town": "ns",
            "country": "serb"
        },
        "offers": {
            "wifi": true,
            "parking": false,
            "kitchen": true,
            "airConditioner": true,
            "petsAllowed": true
        },
        "pictures": "https://gdenaodmor.rs/wp-content/uploads/2021/08/jezero-perucac-01.jpg",
        "minimalAllowedGuests": 10,
        "maximalAllowedGuests": 20,
        "typeOfPayment": "PER_NIGHT",
        "price": {
            "regularPrice": 20.0,
            "temporaryPrice": 2.5
        },
        "isAutoConfirmed": false
    }
);

