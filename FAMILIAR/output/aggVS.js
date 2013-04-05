var flare = {
aggVS: {
	 VSApplicationRequirement: {
		 Task: {
				 Counting: 10000 ,
				 Tracking: 10001 ,
				 BehaviourRecognition: {
					 IntrusionDetection: 10002,
				},
		},
		 Scene: {
			 APrioriKnowledge: {
				 Scenery3DModel: 10003,
				 BehaviourLibrary: 10004,
				 DeploymentArchitecture: {
					 Sensor: {
						 SensorProperty: {
								 MonoSensor: 10005 ,
								 MultiSensor: 10006 ,
						},
						 Camera: {
							 DepthOfField: 10007,
							 CameraType: {
									 BlackAndWhite: 10008 ,
									 Color: 10009 ,
									 Infrared: 10010 ,
							},
							 FramesPerSecond: {
									 HighFrameRate: {
										 VingtCinqFrSec: 10011,
									},
									 LowFrameRate: 10012 ,
									 IntermediateFrameRate: 10013 ,
							},
							 FieldOfView: {
									 Large: 10014 ,
									 Narrow: 10015 ,
							},
							 Resolution: 10016,
						},
						 SensorView: {
								 SideView: 10017 ,
								 TopView: 10018 ,
								 NormalView: 10019 ,
						},
					},
					 Server: 10020,
					 ClientDefined: 10021,
					 ProgrammerDefined: 10022,
				},
			},
			 Environment: {
				 Noise: {
					 LightingVariation: {
							 Flashes: 10023 ,
							 HeadLight: 10024 ,
							 Shadows: 10025 ,
					},
					 BackgroundMovement: {
							 SandOrDust: 10026 ,
							 SeaOceanWaves: 10027 ,
							 VegetationMovement: 10028 ,
					},
				},
				 LightingType: {
						 ArtificialLight: 10029 ,
						 NaturalLight: 10030 ,
				},
				 LightingConditions: {
						 Indoors: 10031 ,
						 Outdoors: {
							 TimeOfDay: {
									 Day: 10032 ,
									 Night: 10033 ,
							},
						},
				},
			},
		},
		 ObjectOfInterest: {
			 Cardinality: {
					 SingleObject: 10034 ,
					 GroupOfObjects: 10035 ,
			},
			 Sort: {
					 Natural: {
						 Person: 10036,
					},
					 Manufactured: {
						 Vehicle: 10037,
					},
			},
			 Mobility: {
					 Movable: {
							 ExternallyPropelled: 10038 ,
							 SelfPropelled: 10039 ,
					},
					 Immovable: 10040 ,
			},
		},
		 QualityOfService: {
				 ComputerLoad: 10041 ,
				 ResponseTime: 10042 ,
				 Quality: {
						 Sensitivity: 10043 ,
						 Precision: {
							 LessPrecision: 10044,
						},
				},
		},
	},
	 VSPlatform: {
		 ScenarioRecognition: 10045,
		 FrameToFrameTracking: 10046,
		 Clustering: {
				 DistanceRGBAnd3DClustering: 10047 ,
				 FastClustering: 10048 ,
				 KMeansClustering: 10049 ,
		},
		 TrackingDataFusion: 10050,
		 ImageAcquisition: 10051,
		 ReferenceImageUpdating: 10052,
		 SegmentationModule: {
			 KernelFunction: {
				 ShadowElimination: 10053,
				 ColorType: {
						 GreyLevel: 10054 ,
						 SystemColor: 10055 ,
				},
				 ObjectAspect: {
						 Edge: 10056 ,
						 Region: 10057 ,
				},
			},
			 TraversalAlgorithm: {
				 TraversalParameters: {
						 WithWindow: 10058 ,
						 WithMask: 10059 ,
						 Fast: 10060 ,
				},
				 GridStep: {
						 HighGridStep: 10061 ,
						 LowGridStep: 10062 ,
				},
			},
			 SegmFineTune: 10063,
			 Threshold: {
					 LowThreshold: 10064 ,
					 MidThreshold: 10065 ,
					 HighThreshold: 10066 ,
			},
		},
		 LongTermTracking: 10067,
		 ClassificationModule: {
			 Split: {
					 SplitStandard: 10068 ,
					 SplitGravityCenter: 10069 ,
			},
			 Model: {
					 ModelType: {
							 OmegaModel: 10070 ,
							 ThreeDModel: 10071 ,
					},
					 Ellipse: 10072 ,
					 Shape: 10073 ,
					 Parallelepiped: 10074 ,
					 GravityCenter: 10075 ,
			},
			 Contour: 10076,
			 Merge: {
					 FusionGravityCenter: 10077 ,
					 FusionStandard: 10078 ,
			},
			 ClassificationStandard: {
				 Density: 10079,
			},
		},
	},
}

};

