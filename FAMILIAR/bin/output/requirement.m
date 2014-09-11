VideoSurveillanceApplication : Scene QualityOfService+ ObjectOfInterest Task+ :: _VideoSurveillanceApplication ;

Scene : Environment APrioriKnowledge :: _Scene ;

Environment : [Noise] LightingConditions+ :: _Environment ;

Noise : BackgroundMovement* LightingVariation* :: _Noise ;

BackgroundMovement : SandOrDust
	| SeaOceanWaves
	| VegetationMovement ;

LightingVariation : Flashes
	| Shadows ;

LightingConditions : Indoors
	| Outdoors
	| LightingType ;

Outdoors : TimeOfDay+ :: _Outdoors ;

TimeOfDay : Day
	| Night ;

LightingType : ArtificialLight
	| NaturalLight ;

APrioriKnowledge : [DeploymentArchitecture] [BehaviourLibrary] Scenery3DModel :: _APrioriKnowledge ;

DeploymentArchitecture : MonoSensor Server [ProgrammerDefined] MultiSensor Sensor [ClientDefined] :: _DeploymentArchitecture ;

Sensor : SensorView Camera :: _Sensor ;

SensorView : Normal
	| SideView
	| TopView ;

Camera : FieldOfView Resolution CameraType DepthOfField FramesPerSecond :: _Camera ;

FieldOfView : Large
	| Narrow ;

CameraType : BlackAndWhite
	| InfraRed
	| Color ;

QualityOfService : ComputerLoad
	| ResponseTime
	| Quality ;

Quality : Sensitivity
	| Precision ;

ObjectOfInterest : Mobility Cardinality Sort+ :: _ObjectOfInterest ;

Mobility : Movable
	| Immovable ;

Movable : ExternallyPropelled
	| SelfPropelled ;

Cardinality : SingleObject
	| GroupOfObjects ;

Sort : Natural
	| Manufactured ;

Natural : Person :: _Natural ;

Manufactured : Vehicle :: _Manufactured ;

Task : Counting
	| Tracking
	| BehaviourRecognition ;

BehaviourRecognition : [IntrusionDetection] :: _BehaviourRecognition ;

%%

Counting implies Large ;
Counting implies Precision ;

