# shortenURL

## Introduction

This project will generate a short URL that can map to your orginial URL. Also, users can know the usage of the URL. 

## How to shorten a URL

### Append random number (0 ~ 1000) after orginal URL

### Original URL + random number — MD5 —> 128-bits hash

### 128-bits hash — Base64 —> 22 characters
- We can express 64 different characters with 6 bits. (2^6=64)
- 128-bits hash can be encoded into 21 characters but the rest 2 bits cannot be converted. (128 / 6 = 21 … 2)
-  To make 128-bits hash divided, we add 4 extra bits to this hash, so it is 132 bits now.
- Thus, encoding this new hash value can generate 22 characters. (132 / 6 = 22)

### Pick 6 characters randomly from 22 characters
