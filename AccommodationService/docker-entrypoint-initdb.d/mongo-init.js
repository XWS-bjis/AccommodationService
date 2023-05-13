db = new Mongo().getDB('booking');

db.createCollection('accommodation');

db.accommodation.insert (
    {
        "hostId": null,
        "name": "ime",
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
        "pictures": null,
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

