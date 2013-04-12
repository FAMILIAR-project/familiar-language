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
				 DeploymentArchitecture: {
					 Server: 10004,
					 ClientDefined: 10005,
					 ProgrammerDefined: 10006,
					 Sensor: {
						 SensorProperty: {
								 MonoSensor: 10007 ,
								 MultiSensor: 10008 ,
						},
						 Camera: {
							 Resolution: 10009,
							 CameraType: {
									 BlackAndWhite: 10010 ,
									 Color: 10011 ,
									 Infrared: 10012 ,
							},
							 FieldOfView: {
									 Large: 10013 ,
									 Narrow: 10014 ,
							},
							 FramesPerSecond: {
									 HighFrameRate: {
										 VingtCinqFrSec: 10015,
									},
									 LowFrameRate: 10016 ,
									 IntermediateFrameRate: 10017 ,
							},
							 DepthOfField: 10018,
						},
						 SensorView: {
								 SideView: 10019 ,
								 TopView: 10020 ,
								 NormalView: 10021 ,
						},
					},
				},
				 BehaviourLibrary: 10022,
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
		 QualityOfService: {
				 ComputerLoad: 10034 ,
				 ResponseTime: 10035 ,
				 Quality: {
						 Sensitivity: 10036 ,
						 Precision: {
							 LessPrecision: 10037,
						},
				},
		},
		 ObjectOfInterest: {
			 Mobility: {
					 Movable: {
							 ExternallyPropelled: 10038 ,
							 SelfPropelled: 10039 ,
					},
					 Immovable: 10040 ,
			},
			 Cardinality: {
					 SingleObject: 10041 ,
					 GroupOfObjects: 10042 ,
			},
			 Sort: {
					 Natural: {
						 Person: 10043,
					},
					 Manufactured: {
						 Vehicle: 10044,
					},
			},
		},
	},
	 VSPlatform: {
		 TrackingDataFusion: 10045,
		 ScenarioRecognition: 10046,
		 SegmentationModule: {
			 TraversalAlgorithm: {
				 GridStep: {
						 HighGridStep: 10047 ,
						 LowGridStep: 10048 ,
				},
				 TraversalParameters: {
						 WithWindow: 10049 ,
						 WithMask: 10050 ,
						 Fast: 10051 ,
				},
			},
			 Threshold: {
					 LowThreshold: 10052 ,
					 MidThreshold: 10053 ,
					 HighThreshold: 10054 ,
			},
			 KernelFunction: {
				 ShadowElimination: 10055,
				 ObjectAspect: {
						 Edge: 10056 ,
						 Region: 10057 ,
				},
				 ColorType: {
						 GreyLevel: 10058 ,
						 SystemColor: 10059 ,
				},
			},
			 SegmFineTune: 10060,
		},
		 FrameToFrameTracking: 10061,
		 Clustering: {
				 DistanceRGBAnd3DClustering: 10062 ,
				 FastClustering: 10063 ,
				 KMeansClustering: 10064 ,
		},
		 ReferenceImageUpdating: 10065,
		 LongTermTracking: 10066,
		 ImageAcquisition: 10067,
		 ClassificationModule: {
			 Merge: {
					 FusionGravityCenter: 10068 ,
					 FusionStandard: 10069 ,
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
			 Split: {
					 SplitStandard: 10076 ,
					 SplitGravityCenter: 10077 ,
			},
			 ClassificationStandard: {
				 Density: 10078,
			},
			 Contour: 10079,
		},
	},
}

};

