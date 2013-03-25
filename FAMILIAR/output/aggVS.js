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
				 Scenery3DModel: 10011,
				 DeploymentArchitecture: {
					 Sensor: {
						 Camera: {
							 Resolution: 10012,
							 FieldOfView: {
									 Large: 10013 ,
									 Narrow: 10014 ,
							},
							 CameraType: {
									 BlackAndWhite: 10015 ,
									 Color: 10016 ,
									 Infrared: 10017 ,
							},
							 DepthOfField: 10018,
							 FramesPerSecond: {
									 HighFrameRate: {
										 VingtCinqFrSec: 10019,
									},
									 LowFrameRate: 10020 ,
									 IntermediateFrameRate: 10021 ,
							},
						},
						 SensorProperty: {
								 MonoSensor: 10022 ,
								 MultiSensor: 10023 ,
						},
						 SensorView: {
								 SideView: 10024 ,
								 TopView: 10025 ,
								 NormalView: 10026 ,
						},
					},
					 ClientDefined: 10027,
					 Server: 10028,
					 ProgrammerDefined: 10029,
				},
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
	},
	 VSPlatform: {
		 ClassificationModule: {
			 Merge: {
					 FusionGravityCenter: 10045 ,
					 FusionStandard: 10046 ,
			},
			 Split: {
					 SplitStandard: 10047 ,
					 SplitGravityCenter: 10048 ,
			},
			 Contour: 10049,
			 ClassificationStandard: {
				 Density: 10050,
			},
			 Model: {
					 ModelType: {
							 OmegaModel: 10051 ,
							 ThreeDModel: 10052 ,
					},
					 Ellipse: 10053 ,
					 Shape: 10054 ,
					 Parallelepiped: 10055 ,
					 GravityCenter: 10056 ,
			},
		},
		 Clustering: {
				 DistanceRGBAnd3DClustering: 10057 ,
				 FastClustering: 10058 ,
				 KMeansClustering: 10059 ,
		},
		 ImageAcquisition: 10060,
		 ScenarioRecognition: 10061,
		 FrameToFrameTracking: 10062,
		 ReferenceImageUpdating: 10063,
		 TrackingDataFusion: 10064,
		 SegmentationModule: {
			 TraversalAlgorithm: {
				 GridStep: {
						 HighGridStep: 10065 ,
						 LowGridStep: 10066 ,
				},
				 TraversalParameters: {
						 WithWindow: 10067 ,
						 WithMask: 10068 ,
						 Fast: 10069 ,
				},
			},
			 SegmFineTune: 10070,
			 KernelFunction: {
				 ColorType: {
						 GreyLevel: 10071 ,
						 SystemColor: 10072 ,
				},
				 ObjectAspect: {
						 Edge: 10073 ,
						 Region: 10074 ,
				},
				 ShadowElimination: 10075,
			},
			 Threshold: {
					 LowThreshold: 10076 ,
					 MidThreshold: 10077 ,
					 HighThreshold: 10078 ,
			},
		},
		 LongTermTracking: 10079,
	},
}

};

