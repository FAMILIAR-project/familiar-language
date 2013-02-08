var flare = {
aggVS: {
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
					 ProgrammerDefined: 10011,
					 Server: 10012,
					 ClientDefined: 10013,
					 Sensor: {
						 Camera: {
							 FieldOfView: {
									 Large: 10014 ,
									 Narrow: 10015 ,
							},
							 CameraType: {
									 BlackAndWhite: 10016 ,
									 Color: 10017 ,
									 Infrared: 10018 ,
							},
							 FramesPerSecond: {
									 HighFrameRate: {
										 VingtCinqFrSec: 10019,
									},
									 LowFrameRate: 10020 ,
									 IntermediateFrameRate: 10021 ,
							},
							 Resolution: 10022,
							 DepthOfField: 10023,
						},
						 SensorView: {
								 SideView: 10024 ,
								 TopView: 10025 ,
								 NormalView: 10026 ,
						},
						 SensorProperty: {
								 MonoSensor: 10027 ,
								 MultiSensor: 10028 ,
						},
					},
				},
				 Scenery3DModel: 10029,
				 BehaviourLibrary: 10030,
			},
		},
		 ObjectOfInterest: {
			 Mobility: {
					 Movable: {
							 ExternallyPropelled: 10031 ,
							 SelfPropelled: 10032 ,
					},
					 Immovable: 10033 ,
			},
			 Sort: {
					 Natural: {
						 Person: 10034,
					},
					 Manufactured: {
						 Vehicle: 10035,
					},
			},
			 Cardinality: {
					 SingleObject: 10036 ,
					 GroupOfObjects: 10037 ,
			},
		},
		 QualityOfService: {
				 ComputerLoad: 10038 ,
				 ResponseTime: 10039 ,
				 Quality: {
						 Sensitivity: 10040 ,
						 Precision: {
							 LessPrecision: 10041,
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
	},
	 VSPlatform: {
		 LongTermTracking: 10045,
		 ImageAcquisition: 10046,
		 ReferenceImageUpdating: 10047,
		 Clustering: {
				 DistanceRGBAnd3DClustering: 10048 ,
				 FastClustering: 10049 ,
				 KMeansClustering: 10050 ,
		},
		 ScenarioRecognition: 10051,
		 FrameToFrameTracking: 10052,
		 TrackingDataFusion: 10053,
		 ClassificationModule: {
			 Merge: {
					 FusionGravityCenter: 10054 ,
					 FusionStandard: 10055 ,
			},
			 Contour: 10056,
			 Split: {
					 SplitStandard: 10057 ,
					 SplitGravityCenter: 10058 ,
			},
			 Model: {
					 ModelType: {
							 OmegaModel: 10059 ,
							 ThreeDModel: 10060 ,
					},
					 Ellipse: 10061 ,
					 Shape: 10062 ,
					 Parallelepiped: 10063 ,
					 GravityCenter: 10064 ,
			},
			 ClassificationStandard: {
				 Density: 10065,
			},
		},
		 SegmentationModule: {
			 TraversalAlgorithm: {
				 GridStep: {
						 HighGridStep: 10066 ,
						 LowGridStep: 10067 ,
				},
				 TraversalParameters: {
						 WithWindow: 10068 ,
						 WithMask: 10069 ,
						 Fast: 10070 ,
				},
			},
			 SegmFineTune: 10071,
			 KernelFunction: {
				 ColorType: {
						 GreyLevel: 10072 ,
						 SystemColor: 10073 ,
				},
				 ObjectAspect: {
						 Edge: 10074 ,
						 Region: 10075 ,
				},
				 ShadowElimination: 10076,
			},
			 Threshold: {
					 LowThreshold: 10077 ,
					 MidThreshold: 10078 ,
					 HighThreshold: 10079 ,
			},
		},
	},
}

};

