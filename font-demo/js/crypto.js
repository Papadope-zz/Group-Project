<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/aes.js"></script>
var key = "U2FsdGVkX18aRVAmJfExorF7/NagaP760Q9tsiV5t3qtu2vVB+/t6vLzDhcSw5iRqqElrdee9O/Y5Dn3R8d6Fh0ah48oNRAAedNRRCkbDgvNMiBQ15LF+yFlY9OE1AX90VlhGlaMNjET+cLPFlLvq4yduBTULBXNM8wR4KTJSVKV6URPETD21F5o/1oH6ApXMXgaWZwvKDiIMRJExRk8AFnXbYewfw5SH3p7VdUZvJOlm6g4AB5esnMHRBsdUwN59tv436Yvo8SJJlSQl16ah64KfX1jH28MPfpTFozxFvacIloJ4EbWlzYZN7FjOtGPcTyPd9a/Vg4Pklq9+VTlPvzt3+VkTYx7//EeCFmnCgkLXpOl8ZQjGeerNVTIeqZVA42rL5gXaLWkRpxAom4KvYAz4loEaJOMgB45USmL0GJvt36Tyjc+l8WAjL3qw0nWOTOhhsjLNkxGcgb/1pKL+3/rqmB5xRdl5yaPuIRQTtShWRMT6q2fnpA+WKGPzGcsKi3d6IjZP1V7SUKeZut0t2koQWK0GdtkLoJsdNkmk6vA+WstVER8flLqh+98ZYZkYLzJYxlj4rWEPZ8TS1mZ8Oy3g5VinVKWDZ/VKSvfpsEYLBLIAaIl/1A8UX5KbYy58KMHhqmmfY/9F8n4UbBaR7GEm9PV4yCxjxnH3tYVyEck0TDIIV4KAuoQp7V29boh6LhGxXo1VADoDrBZHACbGA==";
// var data = CryptoJS.AES.decrypt("Message", key); // Encryption Part
var decrypted = CryptoJS.AES.decrypt(data, key).toString(CryptoJS.enc.Utf8);