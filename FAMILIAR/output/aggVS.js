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
		 QualityOfService: {
				 ComputerLoad: 10003 ,
				 ResponseTime: 10004 ,
				 Quality: {
						 Sensitivity: 10005 ,
						 Precision: {
							 LessPrecision: 10006,
						},
				},
		},
		 Scene: {
			 Environment: {
				 Noise: {
					 LightingVariation: {
							 Flashes: 10007 ,
							 HeadLight: 10008 ,
							 Shadows: 10009 ,
					},
					 BackgroundMovement: {
							 SandOrDust: 10010 ,
							 SeaOceanWaves: 10011 ,
							 VegetationMovement: 10012 ,
					},
				},
				 LightingType: {
						 ArtificialLight: 10013 ,
						 NaturalLight: 10014 ,
				},
				 LightingConditions: {
						 Indoors: 10015 ,
						 Outdoors: {
							 TimeOfDay: {
									 Day: 10016 ,
									 Night: 10017 ,
							},
						},
				},
			},
			 APrioriKnowledge: {
				 BehaviourLibrary: 10018,
				 Scenery3DModel: 10019,
				 DeploymentArchitecture: {
					 Sensor: {
						 SensorView: {
								 SideView: 10020 ,
								 TopView: 10021 ,
								 NormalView: 10022 ,
						},
						 Camera: {
							 DepthOfField: 10023,
							 FramesPerSecond: {
									 HighFrameRate: {
										 VingtCinqFrSec: 10024,
									},
									 LowFrameRate: 10025 ,
									 IntermediateFrameRate: 10026 ,
							},
							 Resolution: 10027,
							 FieldOfView: {
									 Large: 10028 ,
									 Narrow: 10029 ,
							},
							 CameraType: {
									 BlackAndWhite: 10030 ,
									 Color: 10031 ,
									 Infrared: 10032 ,
							},
						},
						 SensorProperty: {
								 MonoSensor: 10033 ,
								 MultiSensor: 10034 ,
						},
					},
					 ProgrammerDefined: 10035,
					 Server: 10036,
					 ClientDefined: 10037,
				},
			},
		},
		 ObjectOfInterest: {
			 Sort: {
					 Natural: {
						 Person: 10038,
					},
					 Manufactured: {
						 Vehicle: 10039,
					},
			},
			 Cardinality: {
					 SingleObject: 10040 ,
					 GroupOfObjects: 10041 ,
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
		 ImageAcquisition: 10045,
		 TrackingDataFusion: 10046,
		 SegmentationModule: {
			 Threshold: {
					 LowThreshold: 10047 ,
					 MidThreshold: 10048 ,
					 HighThreshold: 10049 ,
			},
			 KernelFunction: {
				 ShadowElimination: 10050,
				 ColorType: {
						 GreyLevel: 10051 ,
						 SystemColor: 10052 ,
				},
				 ObjectAspect: {
						 Edge: 10053 ,
						 Region: 10054 ,
				},
			},
			 TraversalAlgorithm: {
				 GridStep: {
						 HighGridStep: 10055 ,
						 LowGridStep: 10056 ,
				},
				 TraversalParameters: {
						 WithWindow: 10057 ,
						 WithMask: 10058 ,
						 Fast: 10059 ,
				},
			},
			 SegmFineTune: 10060,
		},
		 ScenarioRecognition: 10061,
		 ReferenceImageUpdating: 10062,
		 ClassificationModule: {
			 Model: {
					 ModelType: {
							 OmegaModel: 10063 ,
							 ThreeDModel: 10064 ,
					},
					 Ellipse: 10065 ,
					 Shape: 10066 ,
					 Parallelepiped: 10067 ,
					 GravityCenter: 10068 ,
			},
			 Contour: 10069,
			 Merge: {
					 FusionGravityCenter: 10070 ,
					 FusionStandard: 10071 ,
			},
			 Split: {
					 SplitStandard: 10072 ,
					 SplitGravityCenter: 10073 ,
			},
			 ClassificationStandard: {
				 Density: 10074,
			},
		},
		 FrameToFrameTracking: 10075,
		 LongTermTracking: 10076,
		 Clustering: {
				 DistanceRGBAnd3DClustering: 10077 ,
				 FastClustering: 10078 ,
				 KMeansClustering: 10079 ,
		},
	},
}

};

