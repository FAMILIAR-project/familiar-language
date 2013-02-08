/*---------------------------------------------------------------------------*
 | Copyright (C) 2000 ConfigIt Software (www.configit-software.com)
 |
 | Product Model  : bike.pm
 | Description    : A product model for configurable bicycles
 | PM version     : 3.0
 *---------------------------------------------------------------------------*/

type

/*--------------------------- The person ------------------------------------*/

/* The person's gender */
Gender : [
  "Male",
  "Female"
];

/* The person's height */
PersonHeight : [ 
  "150-160 cm",
  "160-170 cm",
  "170-180 cm",
  "180-190 cm",
  "190-200 cm" 
];

/* The type of bicycle */
BikeType : [
  "City Bike",
  "Grandma Bike",
  "Mountain Bike",
  "Racer Bike"
];

/* The person */
Person : {
  public gender   : Gender;
  public height   : PersonHeight;
  public biketype : BikeType;
};

/*--------------------------- Accessories -----------------------------------*/

/* Extra accessories */
Extra_cat : {
  public Carrier     : boolean;
  public Mudguard    : boolean;
  public Lock        : boolean;
  public Pump        : boolean;
  public Bottle      : boolean;
  public Basket      : boolean;
  public Cateye      : boolean;
  public Sidereflex  : boolean;
  public Frontreflex : boolean;
  public Propstand   : boolean;
rule
  /* A carrier requires a mudguard */
  Carrier -> Mudguard;
  /* You cannot have both a pump and a bottle */
  not (Pump and Bottle);
};

/*------------------------------- Pedals ------------------------------------*/
/* Bicycle pedals */
Pedal : [ 
  "Black Plastic",
  "Campagnolo Chorus",
  "Campagnolo Record",
  "PD 5500",
  "PD 6600",
  "PD C101",
  "PD C105",
  "PD M434",
  "PD M545" 
];

/* The type of pedal */
PedalType : [
  "Standard",
  "SPD",
  "Clip"
];

/* Pedal model */
Pedal_cat : {
  public sku : Pedal;
  private pedaltype : PedalType;
rule
  (sku="PD 6600" and pedaltype="SPD") or
  (sku="PD 5500" and pedaltype="SPD") or
  (sku="PD M545" and pedaltype="Clip") or
  (sku="PD M434" and pedaltype="Clip") or
  (sku="Campagnolo Record" and pedaltype="SPD") or
  (sku="Campagnolo Chorus" and pedaltype="SPD") or
  (sku="PD C105" and pedaltype="Standard") or
  (sku="Black Plastic" and pedaltype="Standard") or
  (sku="PD C101" and pedaltype="Standard");
};

/*------------------------------- Shoes -------------------------------------*/
/* Shoes */
Shoe : [
  "No shoes",
  "SH R072",
  "SH R090",
  "SH R150",
  "SH R212" 
];

/* Shoe model */
Shoe_cat : {
  public sku : Shoe;
  private pedaltype : PedalType;
rule
 (sku="SH R212" and pedaltype="SPD") or
 (sku="SH R150" and pedaltype="Clip") or
 (sku="SH R090" and pedaltype="SPD") or
 (sku="SH R072" and pedaltype="Clip") or
 (sku="No shoes");
};


/*-------------------------------- Rims -------------------------------------*/
/* The rims for the tires */
Rim : [ 
  "Campagnolo Atlanta Aero",
  "Campagnolo Mexico Aero",
  "Campagnolo Moskva Aero",
  "Campagnolo Proton",
  "Cosmos",
  "Cross",
  "CXP 33",
  "Helium",
  "MA3",
  "Open Pro",
  "T519",
  "X221" 
];

/* Frame or rim heights */
Height : [
  "50 cm",
  "65 cm",
  "70 cm"
];

/* The width of a rim */
Width : [
  "0.85 cm",
  "1.00 cm",
  "1.25 cm",
  "1.50 cm",
  "1.75 cm" 
];

/* Model of a rim */
Rim_cat : {
  public sku : Rim;
  private height : Height;
  private width  : Width;
rule
  (sku="MA3" and height="70 cm" and width="0.85 cm") or
  (sku="T519" and height="70 cm" and width="1.00 cm") or
  (sku="CXP 33" and height="70 cm" and width="1.25 cm") or
  (sku="Open Pro" and height="70 cm" and width="1.00 cm") or
  (sku="X221" and height="65 cm" and width="1.50 cm") or
  (sku="Cosmos" and height="65 cm" and width="1.25 cm") or
  (sku="Cross" and height="50 cm" and width="1.50 cm") or
  (sku="Campagnolo Mexico Aero" and height="65 cm" and width="1.00 cm") or
  (sku="Campagnolo Proton"and height="65 cm" and width="0.85 cm") or
  (sku="Campagnolo Moskva Aero" and height="65 cm" and width="0.85 cm") or
  (sku="Campagnolo Atlanta Aero" and height="65 cm" and width="1.00 cm") or
  (sku="Helium" and height="50 cm" and width="1.25 cm");
};

/* --------------------- Tires ------------------------------ */
/* The tires on the bicycle */
Tire : [ 
  "All Weather",
  "Atlanta",
  "Beaumont",
  "Courier",
  "Extreme",
  "Kenda",
  "Lizzard",
  "Panaracer Avventura",
  "Panaracer Category Pro",
  "Panaracer Everride",
  "Panaracer Stradius",
  "Panaracer Tourguard",
  "Roma",
  "Tecno",
  "Track",
  "Triatlon" 
];

/* Tire profile */
Profile : [
  "19 mm",
  "20 mm",
  "21 mm",
  "22 mm",
  "23 mm",
  "25 mm",
  "28 mm",
  "30 mm",
  "32 mm",
  "35 mm",
  "38 mm" 
];

/* Model of a tire */
Tire_cat : {
  public sku      : Tire;
  private height  : Height;
  private width   : Width;
  private profile : Profile;
rule
  (sku="Triatlon" and profile="20 mm" and height="65 cm" and width="1.00 cm") or
  (sku="Courier" and (profile="23 mm" or profile="32 mm") and height="70 cm" and
   (width="0.85 cm" or width="1.25 cm")) or
  (sku="Tecno" and (profile="20 mm" or profile="23 mm" or profile="28 mm") and height="70 cm" and
   (width="1.00 cm" or width="1.25 cm")) or
  (sku="Roma" and (profile="19 mm" or profile="22 mm" or profile="25 mm") and height="70 cm" and 
   (width="0.85 cm" or width="1.00 cm")) or
  (sku="Lizzard" and (profile="35 mm" or profile="38 mm") and height="70 cm" and 
   (width="1.50 cm" or width="1.75 cm")) or
  (sku="Atlanta" and (profile="20 mm" or profile="21 mm") and height="70 cm" and width="1.00 cm") or
  (sku="Track" and profile="22 mm" and height="70 cm" and width="0.85 cm") or
  (sku="Extreme" and profile="21 mm" and height="70 cm" and width="1.00 cm") or
  (sku="All Weather" and (profile="20 mm" or profile="22 mm") and height="70 cm" and width="1.25 cm") or
  (sku="Beaumont" and profile="20 mm" and height="65 cm" and (width="1.25 cm" or width="1.50 cm")) or
  (sku="Panaracer Avventura" and profile="20 mm" and height="65 cm" and
   (width="1.25 cm" or width="1.00 cm")) or
  (sku="Panaracer Category Pro" and (profile="20 mm" or profile="22 mm") and (height="65 cm" or height="70 cm") and 
   (width="1.00 cm" or width="1.25 cm")) or
  (sku="Panaracer Everride" and (profile="22 mm" or profile="25 mm") and (height="65 cm" or height="70 cm") and 
   (width="1.00 cm" or width="1.25 cm")) or
  (sku="Panaracer Tourguard" and (profile="22 mm" or profile="23 mm" or profile="25 mm" or profile="28 mm" or
   profile="30 mm") and (height="65 cm" or height="70 cm") and (width="1.00 cm" or width="1.25 cm")) or
  (sku="Panaracer Stradius" and (profile="20 mm" or profile="19 mm") and (height="65 cm" or height="70 cm") and 
   (width="0.85 cm" or width="1.25 cm")) or
  (sku="Kenda" and profile="35 mm" and height="50 cm" and (width="1.25 cm" or width="1.50 cm"));
};

/* --------------------- Gear set ---------------------------- */
/* Gears */
Gear : [
  "Acera",
  "Campagnolo Avanti Ergopower",
  "Campagnolo Mirage Ergopower",
  "Campagnolo Veloce",
  "Dura Ace",
  "No gears",
  "Shimano 105 STI",
  "Shimano Acer",
  "Shimano Deore",
  "Shimano Nexus",
  "Shimano RSX STI",
  "Sora",
  "Tiagra",
  "Torpedo",
  "Ultegra" 
];

/* The number of gears */
Number : [
  "1",
  "3",
  "4",
  "5",
  "7",
  "16",
  "18",
  "21",
  "24",
  "27" 
];

/* Model of a gear */
Gear_cat : {
  public sku       : Gear;
  public gears     : Number;
  private biketype : BikeType;
  private internal : boolean;
rule
  (sku="Dura Ace" and biketype="Racer Bike" and not internal and gears="18") or
  (sku="Ultegra" and biketype="Racer Bike" and not internal and gears="16") or
  (sku="Tiagra" and biketype="Racer Bike" and not internal and gears="16") or
  (sku="Sora"  and biketype="Mountain Bike" and not internal and gears="21") or 
  (sku="Acera"  and biketype="Mountain Bike" and not internal and gears="24") or
  (sku="Shimano Nexus" and (biketype="City Bike" or biketype="Mountain Bike" or biketype="Grandma Bike")
   and internal=true and (gears = "3" or gears = "4" or gears = "7")) or
  (sku="Shimano RSX STI" and biketype="Racer Bike" and not internal and gears="16") or
  (sku="Shimano 105 STI" and biketype="Racer Bike" and not internal and gears="18") or
  (sku="Campagnolo Avanti Ergopower" and biketype="Racer Bike" and not internal and gears="16") or
  (sku="Campagnolo Mirage Ergopower" and biketype="Racer Bike" and not internal and gears="18") or
  (sku="Campagnolo Veloce" and biketype="Racer Bike" and not internal and gears="18") or
  (sku="Shimano Deore" and biketype="Mountain Bike" and not internal and gears="27") or
  (sku="Shimano Acer" and biketype="Mountain Bike" and not internal and gears="24") or
  (sku="Torpedo" and (biketype="City Bike" or biketype="Grandma Bike") and internal=true
   and (gears="3" or gears="5")) or
  (sku = "No gears" and internal=true and gears="1");

  (biketype="Racer Bike") -> (internal=false);
};

/* --------------------- Frames ----------------------------- */
/* The bicycle frames */
Frame : [
  "Butterfly Classic",
  "Centurion Basic",
  "Centurion Basic Free",
  "Centurion Basic Free Meral",
  "Centurion Basic Light",
  "Centurion Basic Light Meral",
  "Centurion Basic Meral",
  "Centurion Boulevard",
  "Centurion Challenger",
  "Centurion Challenger Lady",
  "Centurion Crazy Point",
  "Centurion Crazy Point Lady",
  "Centurion Dark Image",
  "Centurion Discovery",
  "Centurion Discovery Lady",
  "Centurion Eternity",
  "Centurion Eternity Lady",
  "Centurion Far Out",
  "Centurion Helium",
  "Centurion Invincible",
  "Centurion Nitrogen",
  "Centurion Off Duty",
  "Centurion Oxygen",
  "Centurion Oxygen Meral",
  "Centurion Ultimate",
  "Colibri Street Bike Plus",
  "Faggin 7005",
  "Faggin 7020",
  "Faggin Easton",
  "Jupiter Cruiser",
  "Jupiter Inside",
  "Jupiter Millenium",
  "Jupiter Straight",
  "Kildemoes Logic 32 Derailleur",
  "Kildemoes Primates",
  "Schwinn Mesa",
  "Schwinn Moab 3" 
];


/* Frame colors */
Color : [
  "Black",
  "Black Purple",
  "Blue",
  "Brown",
  "Creme",
  "Green",
  "Grey",
  "Light Blue",
  "Light Green",
  "Purple",
  "Red",
  "Silver",
  "White",
  "Yellow" 
];


/* Frame sizes */
Size : [
  "13\"",
  "14\"",
  "15\"",
  "16\"",
  "17\"",
  "18\"",
  "19\"",
  "20\"",
  "21\"",
  "22\"",
  "23\"",
  "24\"",
  "25\"",
  "28\""
];

/* Frames */
Frame_cat : {
  public sku : Frame;
  public color : Color;
  private biketype : BikeType;
  public size: Size;
  private gender : Gender;
  private internal: boolean;

rule
  (sku="Butterfly Classic" and biketype="Grandma Bike" and
   (order(size)>=order("20\"") and order(size)<=order("23\""))
         and gender="Female" and internal and
         (color="Black" or color="Red" or color="Creme" or color="Blue")) or

  (sku="Centurion Basic Free Meral" and biketype="City Bike" and 
   (size="20\"" or size="22\"" or size="23\"") 
         and gender="Female" and internal
         and color="Light Blue") or

  (sku="Centurion Basic Free" and biketype="City Bike" and 
   (size="22\"" or size="23\"" or size="25\"") 
         and gender="Male" and internal
         and (color="Silver" or color="Light Blue")) or

  (sku="Centurion Basic Light Meral" and biketype="City Bike" and 
   (size="20\"" or size="22\"" or size="23\"") 
         and gender="Female" and internal
         and (color="Silver" or color="Red")) or

  (sku="Centurion Basic Light" and biketype="City Bike" and 
   (size="22\"" or size="23\"" or size="25\"") 
         and gender="Male" and internal
         and (color="Silver" or color="Purple" or color="Red")) or

  (sku="Centurion Basic Meral" and biketype="City Bike" and 
   (size="20\"" or size="22\"" or size="23\"") 
         and gender="Female" and internal
         and (color="Silver" or color="Green" or color="Purple" or color="Red")) or

  (sku="Centurion Basic" and biketype="City Bike" and 
   (size="22\"" or size="23\"" or size="25\"") 
         and gender="Male" and internal
         and (color="Silver" or color="Green" or color="Blue" or color="Black")) or

  (sku="Centurion Boulevard" and biketype="Grandma Bike" and
   (order(size)>=order("20\"") and order(size)<=order("23\""))
         and gender="Female" and internal and
         color="Red") or

  (sku="Centurion Challenger Lady" and biketype="City Bike" and 
   (size="20\"" or size="22\"" or size="23\"") 
         and gender="Female" and internal
         and (color="Purple" or color="Light Blue" or color="Red" or color="Black")) or

  (sku="Centurion Challenger" and biketype="City Bike" and 
   (size="17\"" or size="19\"" or size="20\"" or size="22\"" or size="23\"") and gender="Female"
         and internal
         and (color="Blue" or color="Brown" or color="Light Blue" or color="Purple" or color="Red")) or

  (sku="Centurion Challenger" and biketype="City Bike" and 
   (size="22\"" or size="23\"" or size="25\"") and gender="Male"
         and internal and (color="Light Blue" or color="Blue" or color="Black")) or

  (sku="Centurion Crazy Point Lady" and biketype="Mountain Bike" and 
   (size="15\"" or size="17\"" or size="19\"" or size="20\"") 
         and gender="Female" and internal
         and (color="Silver" or color="Red" or color="Black")) or

  (sku="Centurion Crazy Point" and biketype="Mountain Bike" and 
   (size="15\"" or size="17\"" or size="19\"" or size="20\"" or size="22\"") 
         and gender="Male" and internal
         and (color="Silver" or color="Brown" or color="Blue" or color="Black")) or

  (sku="Centurion Dark Image" and biketype="Mountain Bike" and 
   (size="15\"" or size="17\"" or size="19\"" or size="20\"" or size="22\"") 
         and gender="Male" and internal
         and (color="Silver" or color="Blue")) or

  (sku="Centurion Discovery Lady" and biketype="City Bike" and 
   (size="20\"" or size="22\"" or size="23\"") 
         and gender="Female" and internal
         and (color="Purple" or color="Silver" or color="Red")) or

  (sku="Centurion Discovery" and biketype="City Bike" and 
   (size="22\"" or size="23\"" or size="25\"") 
         and gender="Male" and internal
         and (color="Silver" or color="Green" or color="Purple")) or

  (sku="Centurion Eternity Lady" and biketype="City Bike" and 
   (size="20\"" or size="22\"" or size="23\"") 
         and gender="Female" and internal
         and (color="Silver" or color="Blue")) or

  (sku="Centurion Eternity" and biketype="City Bike" and 
   (size="19\"" or size="20\"" or size="22\"" or size="23\"" or size="25\"") 
         and gender="Male" and internal
         and (color="Silver" or color="Red" or color="Green" or color="Purple" or color="Blue")) or

  (sku="Centurion Far Out" and biketype="Mountain Bike" and 
   (size="15\"" or size="17\"" or size="19\"" or size="20\"" or size="22\"") 
         and gender="Male" and not internal
         and color="Red") or

  (sku="Centurion Helium" and biketype="Racer Bike" and 
   (size="22\"" or size="23\"" or size="24\"" or size="25\"") 
         and gender="Male" and not internal
         and (color="Green" or color="Light Green")) or

  (sku="Centurion Invincible" and biketype="Racer Bike" and 
   (size="22\"" or size="23\"" or size="24\"" or size="25\"") 
         and gender="Male" and not internal
         and (color="Red" or color="Light Green")) or

  (sku="Centurion Nitrogen" and biketype="Racer Bike" and 
   (size="22\"" or size="23\"" or size="24\"" or size="25\"") 
         and gender="Male" and not internal
         and (color="Green" or color="Light Blue")) or

  (sku="Centurion Off Duty" and biketype="Mountain Bike" and 
   (size="15\"" or size="17\"" or size="19\"" or size="20\"" or size="22\"") 
         and gender="Male" and internal
         and (color="Silver" or color="Green" or color="Purple")) or

  (sku="Centurion Oxygen Meral" and biketype="Racer Bike" and 
   (size="19\"" or size="20\"" or size="21\"" or size="22\"") 
         and gender="Female" and not internal
         and color="Silver") or

  (sku="Centurion Oxygen" and biketype="Racer Bike" and 
   (size="22\"" or size="23\"" or size="24\"" or size="25\"") 
         and gender="Male" and not internal
         and (color="Silver" or color="Blue" or color="Purple")) or

  (sku="Centurion Ultimate" and biketype="Racer Bike" and 
   (size="22\"" or size="23\"" or size="24\"" or size="25\"") 
         and gender="Male" and not internal
         and color="Light Blue") or

  (sku="Colibri Street Bike Plus" and biketype="City Bike" and 
   (size="20\"" or size="22\"" or size="24\"") 
         and gender="Male" and internal
         and (color="Black" or color="Grey" or color="Yellow" or 
	      color="Blue" or color="White" or color="Red" or 
	      color="Green" or color="Silver" or color="Light Blue")) or

  (sku="Faggin 7005" and biketype="Racer Bike" and 
   (size="20\"" or size="22\"" or size="23\"" or size="25\"" or size="28\"")
         and gender="Male" and not internal and color="Black") or

  (sku="Faggin 7020" and biketype="Racer Bike" and 
   (size="20\"" or size="22\"" or size="23\"" or size="25\"" or size="28\"") 
         and gender="Male" and not internal and color="Black Purple") or

  (sku="Faggin Easton" and biketype="Racer Bike" and
   (size="20\"" or size="22\"" or size="23\"" or size="25\"" or size="28\"")
         and gender="Male" and not internal and
         (color="Black" or color="White")) or

  (sku="Jupiter Cruiser" and biketype="Mountain Bike" and
   (size="15\"" or size="17\"" or size="19\"" or size="21\"")
        and (gender="Male" or gender="Female")
        and internal and color="Silver") or
  
  (sku="Jupiter Inside" and (biketype="City Bike" or biketype="Mountain Bike") and 
   (size="20\"" or size="21\"" or size="22\"") and gender="Female" and internal and color="Silver") or
  (sku="Jupiter Inside" and (biketype="City Bike" or biketype="Mountain Bike") and 
   (size="21\"" or size="22\"" or size="24\"") and gender="Male" and internal and color="Silver") or

  (sku="Jupiter Millenium" and (biketype="City Bike" or biketype="Mountain Bike") and 
   (size="20\"" or size="21\"" or size="22\"" or size="24\"")
        and gender="Male" and internal and color="Silver") or
  (sku="Jupiter Millenium" and (biketype="City Bike" or biketype="Mountain Bike") and 
   (size="20\"" or size="21\"") and gender="Female" and internal and color="Silver") or

  (sku="Jupiter Straight" and biketype="City Bike" and 
   (size="22\"" or size="23\"" or size="24\"") 
        and (gender="Male" or gender="Female") and internal 
        and (color="Silver" or color="Light Blue")) or

  (sku="Kildemoes Logic 32 Derailleur" and biketype="Mountain Bike" and
   (((size="20\"" or size="22\"" or size="23\"") and gender="Male") or
    ((size="20\"" or size="22\"") and gender="Female"))
    and not internal and color="Silver") or

  (sku="Kildemoes Primates" and biketype="Mountain Bike" and
   (size="16\"" or size="18\"" or size="20\"")
         and gender="Male" and internal and
         color="Green") or

  (sku="Schwinn Mesa" and biketype="Mountain Bike" and
   (order(size)>=order("13\"") and order(size)<=order("23\""))
         and gender="Male" and not internal and
         color="Red") or

  (sku="Schwinn Moab 3" and biketype="Mountain Bike" and
   (order(size)>=order("13\"") and order(size)<=order("23\""))
         and gender="Male" and not internal and
         color="Silver");

  /* All City Bikes and Grandma Bikes have internal gear */
  (biketype="City Bike" or biketype="Grandma Bike") -> (internal);

  /* all racers must have ext gear */
  (biketype="Racer Bike") -> (not internal);
};


/* The product model of a configurable bicycle consists of the
   following components */
variable
  public person : Person;
  public frame  : Frame_cat;
  public extra  : Extra_cat;
  public tires  : Tire_cat;
  public rims   : Rim_cat;
  public gear   : Gear_cat;
  public pedals : Pedal_cat;
  public shoes  : Shoe_cat;

rule
  /* Different rim heights imply different frame sizes */
  case rims.height of
      "50 cm" then (order(frame.size)>=order("15\"") and order(frame.size)<=order("22\""))
    | "65 cm" then (order(frame.size)>=order("17\"") and order(frame.size)<=order("28\""))
    | "70 cm" then (order(frame.size)>=order("19\"") and order(frame.size)<=order("28\""))
    | default true
  esac;

  /* different types of frames require different sizes of tire profiles */
  case frame.biketype of
      "Racer Bike"    then (order(tires.profile)>=order("19 mm") and order(tires.profile)<=order("28 mm") 
                          and not extra.Mudguard and not extra.Basket)
    | "Mountain Bike" then (order(tires.profile)>=order("30 mm") and not extra.Carrier and not extra.Basket)
    | "City Bike"     then (order(tires.profile)>=order("22 mm") and order(tires.profile)<=order("35 mm"))
    | "Grandma Bike"  then (order(tires.profile)>=order("25 mm") and order(tires.profile)<=order("35 mm") 
                            and extra.Carrier and extra.Propstand and extra.Basket)
    | default         true
  esac;

  /* External gear doesn't go with prop stands */
  (not frame.internal) -> (not extra.Propstand);

  /* The tires must fit on the rims */  
  rims.height = tires.height;

  rims.width = tires.width;

  /* The gear must fit on the frame */
  frame.internal = gear.internal;

  /* Shoes and pedals must be of the same type */
  pedals.pedaltype = shoes.pedaltype;

  /* The type of gear must match frame */
  frame.biketype = gear.biketype;

  person.gender = frame.gender;

  person.biketype = frame.biketype;

  if ((frame.biketype="Racer Bike" or frame.biketype="City Bike") and
      (person.height="150-160 cm")) then
    (order(frame.size)>=order("17\"") and order(frame.size)<=order("20\""));

  if ((frame.biketype="Racer Bike" or frame.biketype="City Bike") and
      (person.height="160-170 cm")) then
    (order(frame.size)>=order("19\"") and order(frame.size)<=order("22\""));

  if ((frame.biketype="Racer Bike" or frame.biketype="City Bike") and
      (person.height="170-180 cm")) then
    (order(frame.size)>=order("21\"") and order(frame.size)<=order("24\""));

  if ((frame.biketype="Racer Bike" or frame.biketype="City Bike") and
      (person.height="180-190 cm")) then
    (order(frame.size)>=order("23\"") and order(frame.size)<=order("28\""));

  if ((frame.biketype="Racer Bike" or frame.biketype="City Bike") and
      (person.height="190-200 cm")) then
    (order(frame.size)>=order("25\"") and order(frame.size)<=order("28\""));

  if ((frame.biketype="Mountain Bike") and
      (person.height="150-160 cm")) then
    (order(frame.size)>=order("15\"") and order(frame.size)<=order("18\""));

  if ((frame.biketype="Mountain Bike") and
      (person.height="160-170 cm")) then
    (order(frame.size)>=order("16\"") and order(frame.size)<=order("19\""));

  if ((frame.biketype="Mountain Bike") and
      (person.height="170-180 cm")) then
    (order(frame.size)>=order("17\"") and order(frame.size)<=order("20\""));

  if ((frame.biketype="Mountain Bike") and
      (person.height="180-190 cm" or person.height="190-200 cm")) then
    (order(frame.size)>=order("18\"") and order(frame.size)<=order("22\""));

  if (tires.sku = "Panaracer Avventura" or
      tires.sku = "Panaracer Category Pro" or 
      tires.sku = "Panaracer Tourguard" or 
      tires.sku = "Panaracer Everride" or 
      tires.sku = "Panaracer Stradius") then
     (not extra.Sidereflex);

  case frame.biketype of
      "Racer Bike"    then (pedals.pedaltype <> "Standard")
    | "Mountain Bike" then (pedals.pedaltype <> "Standard")
    | "City Bike"     then (pedals.pedaltype = "Standard")
    | "Grandma Bike"  then (pedals.pedaltype = "Standard")
    | default         true
  esac;

