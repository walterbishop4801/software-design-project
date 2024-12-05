# CHANGELOG for VRS

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [v2.3.1](https://github.com/walterbishop4801/software-design-project/releases/tag/v2.3.1) (2024-12-04)

### Bug Fixes

-  **booking**:  adjust booking price based on the vehicle (#47) ([23b326bb750234b](https://github.com/walterbishop4801/software-design-project/commit/23b326bb750234b0c61f53bddf0817788c37c12f) David Parreño Barbuzano)

### Refactoring

-  **payment**:  refactor payment package ([b2ec04d5d526c5c](https://github.com/walterbishop4801/software-design-project/commit/b2ec04d5d526c5c48b2ff5b5a566bc33b89ece87) David Parreño Barbuzano)
-  **command**:  simplify command invoker initialisation ([2b3ae2a62cfe971](https://github.com/walterbishop4801/software-design-project/commit/2b3ae2a62cfe971e7b7752c24aeeadf0401f310b) David Parreño Barbuzano)

### Testing

-  **payment-auth**:  merge with develop ([89f21c2a96aa3e7](https://github.com/walterbishop4801/software-design-project/commit/89f21c2a96aa3e7771dd3b6860d43ecc7a109c19) Ivor D&#x27;Souza)
-  **payment-auth**:  add payment system tests ([86367f3a67c5b7a](https://github.com/walterbishop4801/software-design-project/commit/86367f3a67c5b7aea8a62639db753e5e7747694e) Ivor D&#x27;Souza)
-  **payment-auth**:  fix payment auth class names ([13a1c7fef7d3b7e](https://github.com/walterbishop4801/software-design-project/commit/13a1c7fef7d3b7eda9ce8c8fc46bb5a26c5a4498) Ivor D&#x27;Souza)

### Other changes

- Refactor Command and Invoker implementation for extensibility and dynamic command execution  ([07a6e9d2bee805e](https://github.com/walterbishop4801/software-design-project/commit/07a6e9d2bee805e9907cf4a28170d649d694a4dd) Rohan Sikder)
- Update application.properties  ([26f2a57347e3477](https://github.com/walterbishop4801/software-design-project/commit/26f2a57347e3477c84791ad17f03f15ffcad16a4) Shane Barden)
- Update application.properties  ([93afece39a3abf8](https://github.com/walterbishop4801/software-design-project/commit/93afece39a3abf82790f7046acab443f5936df61) Rohan Sikder)
- Tests passed  ([6aea75cadaa0eda](https://github.com/walterbishop4801/software-design-project/commit/6aea75cadaa0eda3e8f6f567cd52f8b6f50cb158) Shane Barden)
- Update BookingTests.java  ([2314f8904dc2db7](https://github.com/walterbishop4801/software-design-project/commit/2314f8904dc2db7c5070da8923222dd54f19c7ad) Shane Barden)
- Update BookingTests.java  ([2ba12c21f323714](https://github.com/walterbishop4801/software-design-project/commit/2ba12c21f323714c8a04fe8026f012fabbbfd2be) Shane Barden)
- Create BookingTests.java  ([b3c58bbcddd87ba](https://github.com/walterbishop4801/software-design-project/commit/b3c58bbcddd87ba6d65f00ef670ca4239be67baf) Shane Barden)

## [v2.3.0](https://github.com/walterbishop4801/software-design-project/releases/tag/v2.3.0) (2024-12-04)

### Features

-  add API endpoint to return vehicle and open gate ([ae80f18ad9a3fcb](https://github.com/walterbishop4801/software-design-project/commit/ae80f18ad9a3fcbcee1d1ac0d0321b956a1aa2d0) Rohan Sikder)
-  **strategy-payment**:  add payment strategy ([3d1cbf24822b01a](https://github.com/walterbishop4801/software-design-project/commit/3d1cbf24822b01ad89d48f578ba278f9b4cfe89d) Ivor D&#x27;Souza)
-  include vehicle factory method ([21ec9603387270f](https://github.com/walterbishop4801/software-design-project/commit/21ec9603387270febc81e9d5d821eaca59cdb0e2) David Parreño Barbuzano)
-  **vehicle**:  include vehicle state design pattern ([369b58ffb40d862](https://github.com/walterbishop4801/software-design-project/commit/369b58ffb40d862736d100fb8520f588f9e58e92) David Parreño Barbuzano)
-  **booking**:  encapsulate booking decorator creation ([4a45f4ad5293449](https://github.com/walterbishop4801/software-design-project/commit/4a45f4ad52934497af1df8530958062658ec16d6) David Parreño Barbuzano)

### Bug Fixes

-  postman query find vehicle by id ([ddb90e995a4bf74](https://github.com/walterbishop4801/software-design-project/commit/ddb90e995a4bf74c1f4318b9eed662e93b8ac543) Rohan Sikder)
-  resolved broken tests by adjusting property checks and handling optional fields ([a27ecc43249c555](https://github.com/walterbishop4801/software-design-project/commit/a27ecc43249c555e4591aef20a0d1d791222a533) Rohan Sikder)
-  **mechanic**:  resolved vehicle update issues and added MechanicTests ([f561f66bd439a1b](https://github.com/walterbishop4801/software-design-project/commit/f561f66bd439a1b90a88ffdcd63574b59dd0f268) Rohan Sikder)
-  **mechanic**:  Implement required Observer methods and update state checks in Mechanic class ([ef9c189349012ef](https://github.com/walterbishop4801/software-design-project/commit/ef9c189349012efc2d7d498e6d4772136ca80666) Rohan Sikder)
-  **changelog**:  include the rest of conventional scopes ([82c55dca6fd9883](https://github.com/walterbishop4801/software-design-project/commit/82c55dca6fd9883c41b539bddfdc97760776c7d6) David Parreño Barbuzano)
-  **changelog**:  adjust blank lines ([2bdf208a9197af3](https://github.com/walterbishop4801/software-design-project/commit/2bdf208a9197af3492073089f66dc376557d1706) David Parreño Barbuzano)

### Refactoring

-  change the methods' signature of vehicle factories ([7f1fc8f1ccd513a](https://github.com/walterbishop4801/software-design-project/commit/7f1fc8f1ccd513a68076266da75dd657264cea21) David Parreño Barbuzano)

### Testing

-  include tests for all vehicles and their factories ([d3bfe56700c7e39](https://github.com/walterbishop4801/software-design-project/commit/d3bfe56700c7e39d4665c72ff0f4b77fb61fbc59) David Parreño Barbuzano)
-  include exclude annotations for JaCoCo ([6a093fbf51076e3](https://github.com/walterbishop4801/software-design-project/commit/6a093fbf51076e346b42384dc82b6ce3320cff8c) David Parreño Barbuzano)

### Other changes

- Delete src/test/java/com/ul/vrs/BookingTests.java  ([c35012e4e8f9033](https://github.com/walterbishop4801/software-design-project/commit/c35012e4e8f9033de2eb7afe08fdc4e09f5bfc10) Rohan Sikder)
- Removed enviroment.json maven.yml  ([39073e7ca7149b9](https://github.com/walterbishop4801/software-design-project/commit/39073e7ca7149b969504e3ea94ab40c21a78c977) Rohan Sikder)
- Update maven.yml to make it run on automation branch also for trial  ([2e09a136969af6b](https://github.com/walterbishop4801/software-design-project/commit/2e09a136969af6b9625e0770b81b193cdbc7d188) Rohan Sikder)
- Update maven.yml to include auto postman test  ([388ff305aeca539](https://github.com/walterbishop4801/software-design-project/commit/388ff305aeca539f3da5c1eb3d210276786eac28) Rohan Sikder)
- Updated postman collection for automation  ([1750b186b390c39](https://github.com/walterbishop4801/software-design-project/commit/1750b186b390c3953d3c0f3f26686455c6dab2c3) Rohan Sikder)
- Fixed PMD violation by removing unused import in VehicleManagerService. Updated build to run tests during Maven build process.  ([63cafa7524a4b70](https://github.com/walterbishop4801/software-design-project/commit/63cafa7524a4b708e0c057c0dbe36ce8e1b533d6) Rohan Sikder)
- Update maven.yml  ([ab679e66bfcef5c](https://github.com/walterbishop4801/software-design-project/commit/ab679e66bfcef5c3f5a339d1b3c480b722b73118) Rohan Sikder)
- Create maven.yml  ([1ccea1f6a735bd4](https://github.com/walterbishop4801/software-design-project/commit/1ccea1f6a735bd445bfe115119e7e8c0699c2220) Rohan Sikder)
- Update pom.xml  ([122cb632f5dfece](https://github.com/walterbishop4801/software-design-project/commit/122cb632f5dfece7a17100600b3a9e3e1f1288a8) Shane Barden)
- Add unit tests for RentalSystemService  ([45ee4c9ef78f439](https://github.com/walterbishop4801/software-design-project/commit/45ee4c9ef78f439fc46760d064afb1baae38c030) Rohan Sikder)
- Update BookingTests.java  ([6dd574ff990594f](https://github.com/walterbishop4801/software-design-project/commit/6dd574ff990594f978b551820ad7462eebc3a9d8) Shane Barden)
- Update Mechanic.java  ([73832d306b4bb20](https://github.com/walterbishop4801/software-design-project/commit/73832d306b4bb20b1ae5846378369f73ce38cfa7) Shane Barden)
- Update Mechanic.java  ([765b391e39a5ada](https://github.com/walterbishop4801/software-design-project/commit/765b391e39a5adada81022f352fe448e457b7e7e) Shane Barden)
- Create BookingTests.java  ([15302ba1caafe58](https://github.com/walterbishop4801/software-design-project/commit/15302ba1caafe58f10b3e08760a24c71ba7370f6) Shane Barden)
- Create Mechanic.java  ([09ec294e4bfa401](https://github.com/walterbishop4801/software-design-project/commit/09ec294e4bfa401577f24b04050c74847c01160b) Shane Barden)

## [v2.2.0](https://github.com/walterbishop4801/software-design-project/releases/tag/v2.2.0) (2024-11-20)

### Features

-  **sonarqube**:  add sonaqube integration ([c26c77249e79285](https://github.com/walterbishop4801/software-design-project/commit/c26c77249e792850c199fa88a4899bdc24da4381) Ivor D&#x27;Souza)
-  **config**:  include jacoco and update git-changelog settings ([44b936d9c8e92a7](https://github.com/walterbishop4801/software-design-project/commit/44b936d9c8e92a72a7936f6469ad749314291c74) losedavidpb)

### Bug Fixes

-  **changelog**:  fix hash and include script for changelog formatting ([80ffe94bc000cda](https://github.com/walterbishop4801/software-design-project/commit/80ffe94bc000cda2842eda5c2e7096016b355de8) David Parreño Barbuzano)
-  **postman**:  update collection based on current endpoints ([6e63d518e5080a9](https://github.com/walterbishop4801/software-design-project/commit/6e63d518e5080a913e46d4b0804d9793dbc7f065) David Parreño Barbuzano)
-  **sonarqube**:  delete non-existed properties from spring boot application properties ([1d85c77334ba05d](https://github.com/walterbishop4801/software-design-project/commit/1d85c77334ba05d1382e599e5c1ac1dfa24e2b9e) David Parreño Barbuzano)
-  delete vsc settings ([d7a4e9ec2597539](https://github.com/walterbishop4801/software-design-project/commit/d7a4e9ec25975395fcca17bc8e17e96bd2dce924) losedavidpb)
-  **pmd**:  fix pmd violations ([b32639ba128e012](https://github.com/walterbishop4801/software-design-project/commit/b32639ba128e0125286bae3b4eccddfa9bfba4a7) losedavidpb)
-  **changelog**:  fix tag names and its urls ([16e05d558708e35](https://github.com/walterbishop4801/software-design-project/commit/16e05d558708e35a7ac9efe189fe9bbc6980378c) losedavidpb)
-  **changelog**:  fix tag URL ([c209cf2e3aa5ac6](https://github.com/walterbishop4801/software-design-project/commit/c209cf2e3aa5ac6adacd5fc39ea14ea1129986d4) losedavidpb)
-  **pmd**:  fix version of last commit ([342c8cc9d6bd11b](https://github.com/walterbishop4801/software-design-project/commit/342c8cc9d6bd11bc4b320f3e73b8fde60e48a36f) losedavidpb)
-  **pmd**:  fix violations from subject, observer, fuel and rental system service ([78ecabb7c49b41e](https://github.com/walterbishop4801/software-design-project/commit/78ecabb7c49b41e1c99f33c8dfa11627e7fc8dbf) losedavidpb)
-  **changelog**:  delete the docs section ([7d951be680a2c55](https://github.com/walterbishop4801/software-design-project/commit/7d951be680a2c5586da6f99774e6dbd7353e5014) losedavidpb)
-  **vehicle**:  fixed add/update vehicles by using JsonTypeInfo annotations ([eb20ee2ec0068e6](https://github.com/walterbishop4801/software-design-project/commit/eb20ee2ec0068e6b4ec66f167638fc7065159cc5) Rohan Sikder)

### Other changes

- Changes made to customer to add more custom features as per UML specs  ([320baf1476a5a07](https://github.com/walterbishop4801/software-design-project/commit/320baf1476a5a0739e12593d16ba40ef9d925d09) walterbishop4801)
- Changes made to customer, account and manager as per UML specs  ([e3b40b6a10ec9f4](https://github.com/walterbishop4801/software-design-project/commit/e3b40b6a10ec9f431171c6782ff3cb3d9a7f4eb0) walterbishop4801)
- Implement VRS with Customer, Account and AccountManager  ([5cf02ce2d27b1e0](https://github.com/walterbishop4801/software-design-project/commit/5cf02ce2d27b1e080e9378977db679a96e25b001) walterbishop4801)

## [v2.1.0](https://github.com/walterbishop4801/software-design-project/releases/tag/v2.1.0) (2024-11-14)

### Breaking changes

-  **changelog**:  hot-fix of the versions ([947c06ebce9f430](https://github.com/walterbishop4801/software-design-project/commit/947c06ebce9f430cd41614197b54e7e7809f2171) losedavidpb)

### Features

-  **booking**:  fix booking constructor ([2513e47147e4b8f](https://github.com/walterbishop4801/software-design-project/commit/2513e47147e4b8f957d2b084b9995ec74fbbcf82) Ivor D&#x27;Souza)
-  **revert-booking**:  resolve conflicts with dev ([9dfb8be8699d132](https://github.com/walterbishop4801/software-design-project/commit/9dfb8be8699d132f396bdce2237e61e715d4eec2) Ivor D&#x27;Souza)
-  **booking**:  merge with dev ([b4bcd1be4958fd6](https://github.com/walterbishop4801/software-design-project/commit/b4bcd1be4958fd681fc4e03e5bf55b3435b74620) Ivor D&#x27;Souza)
-  **booking**:  add postman collection ([08e5576876274e1](https://github.com/walterbishop4801/software-design-project/commit/08e5576876274e12be264646d5c7b86f3210e259) Ivor D&#x27;Souza)
-  **booking**:  add booking modules ([e5ede09207b4d1d](https://github.com/walterbishop4801/software-design-project/commit/e5ede09207b4d1d3105157ecf239a61807e9f263) Ivor D&#x27;Souza)
-  **renting**:  controller and service of the rental system ([dfc0b8881cb1c2c](https://github.com/walterbishop4801/software-design-project/commit/dfc0b8881cb1c2c25d9a622eb2e5b8f61678fee6) losedavidpb)

### Bug Fixes

-  **renting**:  fix customise and authenticate methods ([91b7215c111cbe1](https://github.com/walterbishop4801/software-design-project/commit/91b7215c111cbe12d9227829b603377fd23db04a) losedavidpb)
-  **vehicle**:  include getter for name ([7e1ee914023ad79](https://github.com/walterbishop4801/software-design-project/commit/7e1ee914023ad79f5fc21789a03670e64ee94cc0) losedavidpb)
-  **changelog**:  hot-fix of the versions ([947c06ebce9f430](https://github.com/walterbishop4801/software-design-project/commit/947c06ebce9f430cd41614197b54e7e7809f2171) losedavidpb)
-  **vehicle**:  fix services and controllers ([327fd6b9a933180](https://github.com/walterbishop4801/software-design-project/commit/327fd6b9a9331802d579695ce2fd114a2cf9a25a) losedavidpb)
-  **vehicle**:  adjust vehicle based on UML ([ac920979f5fb8a3](https://github.com/walterbishop4801/software-design-project/commit/ac920979f5fb8a36c125ca815e5ba75397252a9c) losedavidpb)

### Other changes

- Factory Implementation within factory module  ([eddcb4c0289cca3](https://github.com/walterbishop4801/software-design-project/commit/eddcb4c0289cca32048400a39a8515ce359e9332) Shane Barden)
- New Factory Package Created  ([9100efb8fa307c6](https://github.com/walterbishop4801/software-design-project/commit/9100efb8fa307c62170a42ce0004699938a93a02) Shane Barden)
- Parameters Updated  ([f1943ad0c58b452](https://github.com/walterbishop4801/software-design-project/commit/f1943ad0c58b452f40ab413cae8128c484269488) Shane Barden)
- Package names updated  ([c947e834e379fcb](https://github.com/walterbishop4801/software-design-project/commit/c947e834e379fcbde6643f60f72f5bc8f1b06fff) Shane Barden)
- Modification of Factory package Location  ([28a04b3372a4626](https://github.com/walterbishop4801/software-design-project/commit/28a04b3372a4626b34dd7492b6fa382394b51972) Shane Barden)
- Vehicle Factories  ([a5c1663f07ad23e](https://github.com/walterbishop4801/software-design-project/commit/a5c1663f07ad23e79bbc49146d20a42ca8df055d) Shane Barden)
- booking: init booking class init booking class ([7e4a8c18ead6f67](https://github.com/walterbishop4801/software-design-project/commit/7e4a8c18ead6f67353ed75bbd45d3e3d1dc0b107) Ivor D&#x27;Souza)

## [v2.0.0](https://github.com/walterbishop4801/software-design-project/releases/tag/v2.0.0) (2024-11-10)

### Breaking changes

-  **rental system**:  add the rental system class ([914fd86b1909a77](https://github.com/walterbishop4801/software-design-project/commit/914fd86b1909a77a0d71aa0a78444d5eea343f3a) losedavidpb)

### Features

-  **rental system**:  add the rental system class ([914fd86b1909a77](https://github.com/walterbishop4801/software-design-project/commit/914fd86b1909a77a0d71aa0a78444d5eea343f3a) losedavidpb)

### Bug Fixes

-  **changelog**:  fix changelog plugin ([e0e6cb4276968bf](https://github.com/walterbishop4801/software-design-project/commit/e0e6cb4276968bfc5ba264528dfe7bbd62bf6c1b) losedavidpb)
-  **package**:  fix the current project package ([8d473652b799111](https://github.com/walterbishop4801/software-design-project/commit/8d473652b7991113294e2884b6c3ad9055cf34b4) losedavidpb)
-  **settings**:  improve dotfiles and add pmd and gitchangelog ([63582b566012610](https://github.com/walterbishop4801/software-design-project/commit/63582b56601261068d92e4bea76053246bfd98c2) losedavidpb)

### Other changes

- Resolved merge conflicts and updated imports and package references  ([8ed604e0fbd3e66](https://github.com/walterbishop4801/software-design-project/commit/8ed604e0fbd3e6627d5be414dea1ab1938214406) Rohan Sikder)
- Based on UML diagram: added interfaces, abstract Vehicle class, concrete vehicle classes (Car, Truck, etc.), Fuel implementations, and enums for Color and VehicleState.  ([f24e1b17526f0fa](https://github.com/walterbishop4801/software-design-project/commit/f24e1b17526f0fa993cfd8506d411d38d38b2acf) Rohan Sikder)
- Added Color and VehicleState enums  ([28c42f3dbc7151f](https://github.com/walterbishop4801/software-design-project/commit/28c42f3dbc7151f626451c6f77f8f0c707eedd6c) Rohan Sikder)

## [v1.3.0](https://github.com/walterbishop4801/software-design-project/releases/tag/v1.3.0) (2024-11-08)

### Other changes

- Create settings.json  ([9b1ff7f4cd0eb85](https://github.com/walterbishop4801/software-design-project/commit/9b1ff7f4cd0eb85dde168d762cf8d3bf1412f743) Shane Barden)
- User Login created  ([71ec31c7406b64f](https://github.com/walterbishop4801/software-design-project/commit/71ec31c7406b64f3d168278f4e2b4cbeae64a77d) walterbishop4801)
- Initial commit  ([f189c81a641e920](https://github.com/walterbishop4801/software-design-project/commit/f189c81a641e920f6c39dc8134339264e4e0a799) walterbishop4801)

## [v1.2.0](https://github.com/walterbishop4801/software-design-project/releases/tag/v1.2.0) (2024-10-24)

### Features

-  **vehicle**:  add CRUD operations for vehicle management ([6680c3a4fb1ffe7](https://github.com/walterbishop4801/software-design-project/commit/6680c3a4fb1ffe7409a20b27ea9d2256a427fb0c) Rohan Sikder)

## [v1.1.0](https://github.com/walterbishop4801/software-design-project/releases/tag/v1.1.0) (2024-10-22)

### Features

-  **init**:  add init project setup ([8c52467a6bfa24c](https://github.com/walterbishop4801/software-design-project/commit/8c52467a6bfa24c4b4034581c2042e07a5e2b98b) Ivor D&#x27;Souza)

### Other changes

- rebase with main  ([fb4bf38792b21bb](https://github.com/walterbishop4801/software-design-project/commit/fb4bf38792b21bbb6f0e55fe73d6780b3dd91d57) Ivor D&#x27;Souza)
- Rename ProjectSpecs to ProjectSpecs.pdf  ([a8abf8ff4606736](https://github.com/walterbishop4801/software-design-project/commit/a8abf8ff4606736501e56204964d31466f80d6b6) David Parreño Barbuzano)
- Rename PDF to ProjectSpecs  ([0a7f11a5f7c5a12](https://github.com/walterbishop4801/software-design-project/commit/0a7f11a5f7c5a12e853bf3918cc8e3b4c8c101cf) David Parreño Barbuzano)
- Include useful links in README  ([6ac9b5b34982751](https://github.com/walterbishop4801/software-design-project/commit/6ac9b5b349827518cf16971ef7ff137bb01bc5cd) David Parreño Barbuzano)

## [v1.0.0](https://github.com/walterbishop4801/software-design-project/releases/tag/v1.0.0) (2024-09-27)

### Other changes

- Create README.md  ([447e6fb1d9d9e15](https://github.com/walterbishop4801/software-design-project/commit/447e6fb1d9d9e1518218d21fedc15dd3a24917e7) walterbishop4801)
- Add files via upload  ([8f2127ddf1d2822](https://github.com/walterbishop4801/software-design-project/commit/8f2127ddf1d2822e9adb2a2542a1510b3a260c2e) walterbishop4801)
