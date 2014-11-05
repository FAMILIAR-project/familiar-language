VideoSurveillanceApplication : Task+ ObjectOfInterest Scene QoS+ :: _VideoSurveillanceApplication ;

Task : Counting
	| BehaviourRecognition+ :: BehaviourRecognition_
	| Tracking ;

BehaviourRecognition : IntrusionDetection ;

ObjectOfInterest : Sort+ Mobility Cardinality :: _ObjectOfInterest ;

Sort : Natural
	| Manufactured ;

Natural : Person ;

Manufactured : Vehicle ;

Mobility : Immovable
	| Movable ;

Movable : SelfPropelled
	| ExternallyPropelled ;

Cardinality : SingleObject
	| GroupOfObjects ;

Scene : AprioriKnowledge Environment :: _Scene ;

AprioriKnowledge : Scenery3DModel [DeploymentArchitecture] [BehaviourLibrary] :: _AprioriKnowledge ;

DeploymentArchitecture : KindSensor Sensor Server [ProgrammerDefined] [ClientDefined] :: _DeploymentArchitecture ;

KindSensor : MonoSensor
	| MultiSensor ;

Sensor : SensorView
	| FramesPerPerson CameraType Resolution FieldOfView DepthOfField :: Camera ;

SensorView : TopView
	| SideView
	| Normal ;

CameraType : BlackAndWhite
	| Color
	| Infrared ;

FieldOfView : Large
	| Narrow ;

Environment : LightningConditions Noise :: _Environment ;

LightningConditions : LightningType+ OutInDoors+ :: _LightningConditions ;

LightningType : NaturalLight
	| ArtificialLight ;

OutInDoors : Outdoors
	| Indoors ;

Outdoors : Night Day :: TimeOfDay ;

Noise : [BackgroundMovement] [LightningVariation] :: _Noise ;

BackgroundMovement : [VegetationMovement] [SandDust] [SeaOceanWaves] :: _BackgroundMovement ;

LightningVariation : [Flashes] [Shadows] :: _LightningVariation ;

QoS : ComputerLoad
	| ResponseTime
	| Quality+ :: Quality_ ;

Quality : Precision
	| Sensitivity ;
