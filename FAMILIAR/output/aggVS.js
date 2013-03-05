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
				 LightingType: {
						 ArtificialLight: 10006 ,
						 NaturalLight: 10007 ,
				},
				 LightingConditions: {
						 Indoors: 10008 ,
						 Outdoors: {
							 TimeOfDay: {
									 Day: 10009 ,
									 Night: 10010 ,
							},
						},
				},
			},
			 APrioriKnowledge: {
				 DeploymentArchitecture: {
					 ProgrammerDefined: 10011,
					 Sensor: {
						 SensorProperty: {
								 MonoSensor: 10012 ,
								 MultiSensor: 10013 ,
						},
						 SensorView: {
								 SideView: 10014 ,
								 TopView: 10015 ,
								 NormalView: 10016 ,
						},
						 Camera: {
							 CameraType: {
									 BlackAndWhite: 10017 ,
									 Color: 10018 ,
									 Infrared: 10019 ,
							},
							 FieldOfView: {
									 Large: 10020 ,
									 Narrow: 10021 ,
							},
							 DepthOfField: 10022,
							 FramesPerSecond: {
									 HighFrameRate: {
										 VingtCinqFrSec: 10023,
									},
									 LowFrameRate: 10024 ,
									 IntermediateFrameRate: 10025 ,
							},
							 Resolution: 10026,
						},
					},
					 ClientDefined: 10027,
					 Server: 10028,
				},
				 BehaviourLibrary: 10029,
				 Scenery3DModel: 10030,
			},
		},
		 ObjectOfInterest: {
			 Sort: {
					 Natural: {
						 Person: 10031,
					},
					 Manufactured: {
						 Vehicle: 10032,
					},
			},
			 Cardinality: {
					 SingleObject: 10033 ,
					 GroupOfObjects: 10034 ,
			},
			 Mobility: {
					 Movable: {
							 ExternallyPropelled: 10035 ,
							 SelfPropelled: 10036 ,
					},
					 Immovable: 10037 ,
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
		 ReferenceImageUpdating: 10045,
		 ClassificationModule: {
			 Contour: 10046,
			 ClassificationStandard: {
				 Density: 10047,
			},
			 Split: {
					 SplitStandard: 10048 ,
					 SplitGravityCenter: 10049 ,
			},
			 Merge: {
					 FusionGravityCenter: 10050 ,
					 FusionStandard: 10051 ,
			},
			 Model: {
					 ModelType: {
							 OmegaModel: 10052 ,
							 ThreeDModel: 10053 ,
					},
					 Ellipse: 10054 ,
					 Shape: 10055 ,
					 Parallelepiped: 10056 ,
					 GravityCenter: 10057 ,
			},
		},
		 LongTermTracking: 10058,
		 TrackingDataFusion: 10059,
		 ImageAcquisition: 10060,
		 SegmentationModule: {
			 SegmFineTune: 10061,
			 TraversalAlgorithm: {
				 GridStep: {
						 HighGridStep: 10062 ,
						 LowGridStep: 10063 ,
				},
				 TraversalParameters: {
						 WithWindow: 10064 ,
						 WithMask: 10065 ,
						 Fast: 10066 ,
				},
			},
			 Threshold: {
					 LowThreshold: 10067 ,
					 MidThreshold: 10068 ,
					 HighThreshold: 10069 ,
			},
			 KernelFunction: {
				 ColorType: {
						 GreyLevel: 10070 ,
						 SystemColor: 10071 ,
				},
				 ShadowElimination: 10072,
				 ObjectAspect: {
						 Edge: 10073 ,
						 Region: 10074 ,
				},
			},
		},
		 FrameToFrameTracking: 10075,
		 Clustering: {
				 DistanceRGBAnd3DClustering: 10076 ,
				 FastClustering: 10077 ,
				 KMeansClustering: 10078 ,
		},
		 ScenarioRecognition: 10079,
	},
}

};

