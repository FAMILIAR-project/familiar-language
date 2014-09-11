var flare = {
VSApplicationRequirement: {
	 Scene: {
		 Environment: {
			 Noise: {
				 BackgroundMovement: {
						 SandOrDust: 10000 ,
						 SeaOceanWaves: 10001 ,
						 VegetationMovement: 10002 ,
				},
				 LightingVariation: {
						 Flashes: 10003 ,
						 HeadLight: 10004 ,
						 Shadows: 10005 ,
				},
			},
			 LightingConditions: {
					 Indoors: 10006 ,
					 Outdoors: {
						 TimeOfDay: {
								 Day: 10007 ,
								 Night: 10008 ,
						},
					},
			},
			 LightingType: {
					 ArtificialLight: 10009 ,
					 NaturalLight: 10010 ,
			},
		},
		 APrioriKnowledge: {
			 DeploymentArchitecture: {
				 Server: 10011,
				 ProgrammerDefined: 10012,
				 Sensor: {
					 SensorView: {
							 SideView: 10013 ,
							 TopView: 10014 ,
							 NormalView: 10015 ,
					},
					 Camera: {
						 FieldOfView: {
								 Large: 10016 ,
								 Narrow: 10017 ,
						},
						 Resolution: 10018,
						 CameraType: {
								 BlackAndWhite: 10019 ,
								 Color: 10020 ,
								 Infrared: 10021 ,
						},
						 DepthOfField: 10022,
						 FramesPerSecond: {
								 HighFrameRate: {
									 VingtCinqFrSec: 10023,
								},
								 LowFrameRate: 10024 ,
								 IntermediateFrameRate: 10025 ,
						},
					},
					 SensorProperty: {
							 MonoSensor: 10026 ,
							 MultiSensor: 10027 ,
					},
				},
				 ClientDefined: 10028,
			},
			 BehaviourLibrary: 10029,
			 Scenery3DModel: 10030,
		},
	},
	 QualityOfService: {
			 ComputerLoad: 10031 ,
			 ResponseTime: 10032 ,
			 Quality: {
					 Sensitivity: 10033 ,
					 Precision: {
						 LessPrecision: 10034,
					},
			},
	},
	 ObjectOfInterest: {
		 Mobility: {
				 Movable: {
						 ExternallyPropelled: 10035 ,
						 SelfPropelled: 10036 ,
				},
				 Immovable: 10037 ,
		},
		 Cardinality: {
				 SingleObject: 10038 ,
				 GroupOfObjects: 10039 ,
		},
		 Sort: {
				 Natural: {
					 Person: 10040,
				},
				 Manufactured: {
					 Vehicle: 10041,
				},
		},
	},
	 Task: {
			 Counting: 10042 ,
			 Tracking: 10043 ,
			 BehaviourRecognition: {
				 IntrusionDetection: 10044,
			},
	},
}

};
