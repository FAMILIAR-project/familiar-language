var flare = {
aggVS: {
	 VSApplicationRequirement: {
		 Scene: {
			 Environment: {
				 Noise: {
					 LightingVariation: {
							 Flashes: 10000 ,
							 HeadLight: 10001 ,
							 Shadows: 10002 ,
					},
					 BackgroundMovement: {
							 SandOrDust: 10003 ,
							 SeaOceanWaves: 10004 ,
							 VegetationMovement: 10005 ,
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
					 ProgrammerDefined: 10011,
					 ClientDefined: 10012,
					 Sensor: {
						 Camera: {
							 CameraType: {
									 BlackAndWhite: 10013 ,
									 Color: 10014 ,
									 Infrared: 10015 ,
							},
							 DepthOfField: 10016,
							 FramesPerSecond: {
									 HighFrameRate: {
										 VingtCinqFrSec: 10017,
									},
									 LowFrameRate: 10018 ,
									 IntermediateFrameRate: 10019 ,
							},
							 FieldOfView: {
									 Large: 10020 ,
									 Narrow: 10021 ,
							},
							 Resolution: 10022,
						},
						 SensorProperty: {
								 MonoSensor: 10023 ,
								 MultiSensor: 10024 ,
						},
						 SensorView: {
								 SideView: 10025 ,
								 TopView: 10026 ,
								 NormalView: 10027 ,
						},
					},
					 Server: 10028,
				},
				 Scenery3DModel: 10029,
				 BehaviourLibrary: 10030,
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
		 Task: {
				 Counting: 10035 ,
				 Tracking: 10036 ,
				 BehaviourRecognition: {
					 IntrusionDetection: 10037,
				},
		},
		 ObjectOfInterest: {
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
			 Mobility: {
					 Movable: {
							 ExternallyPropelled: 10042 ,
							 SelfPropelled: 10043 ,
					},
					 Immovable: 10044 ,
			},
		},
	},
	 VSPlatform: {
		 SegmentationModule: {
			 TraversalAlgorithm: {
				 TraversalParameters: {
						 WithWindow: 10045 ,
						 WithMask: 10046 ,
						 Fast: 10047 ,
				},
				 GridStep: {
						 HighGridStep: 10048 ,
						 LowGridStep: 10049 ,
				},
			},
			 Threshold: {
					 LowThreshold: 10050 ,
					 MidThreshold: 10051 ,
					 HighThreshold: 10052 ,
			},
			 SegmFineTune: 10053,
			 KernelFunction: {
				 ObjectAspect: {
						 Edge: 10054 ,
						 Region: 10055 ,
				},
				 ShadowElimination: 10056,
				 ColorType: {
						 GreyLevel: 10057 ,
						 SystemColor: 10058 ,
				},
			},
		},
		 ClassificationModule: {
			 Contour: 10059,
			 ClassificationStandard: {
				 Density: 10060,
			},
			 Model: {
					 ModelType: {
							 OmegaModel: 10061 ,
							 ThreeDModel: 10062 ,
					},
					 Ellipse: 10063 ,
					 Shape: 10064 ,
					 Parallelepiped: 10065 ,
					 GravityCenter: 10066 ,
			},
			 Split: {
					 SplitStandard: 10067 ,
					 SplitGravityCenter: 10068 ,
			},
			 Merge: {
					 FusionGravityCenter: 10069 ,
					 FusionStandard: 10070 ,
			},
		},
		 LongTermTracking: 10071,
		 ReferenceImageUpdating: 10072,
		 ScenarioRecognition: 10073,
		 TrackingDataFusion: 10074,
		 FrameToFrameTracking: 10075,
		 Clustering: {
				 DistanceRGBAnd3DClustering: 10076 ,
				 FastClustering: 10077 ,
				 KMeansClustering: 10078 ,
		},
		 ImageAcquisition: 10079,
	},
}

};

