var flare = {
aggVS: {
	 VSApplicationRequirement: {
		 Scene: {
			 Environment: {
				 LightingType: {
						 ArtificialLight: 10000 ,
						 NaturalLight: 10001 ,
				},
				 Noise: {
					 BackgroundMovement: {
							 SandOrDust: 10002 ,
							 SeaOceanWaves: 10003 ,
							 VegetationMovement: 10004 ,
					},
					 LightingVariation: {
							 Flashes: 10005 ,
							 HeadLight: 10006 ,
							 Shadows: 10007 ,
					},
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
				 Scenery3DModel: 10011,
				 BehaviourLibrary: 10012,
				 DeploymentArchitecture: {
					 Server: 10013,
					 ProgrammerDefined: 10014,
					 ClientDefined: 10015,
					 Sensor: {
						 Camera: {
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
							 FieldOfView: {
									 Large: 10022 ,
									 Narrow: 10023 ,
							},
							 DepthOfField: 10024,
							 Resolution: 10025,
						},
						 SensorView: {
								 SideView: 10026 ,
								 TopView: 10027 ,
								 NormalView: 10028 ,
						},
						 SensorProperty: {
								 MonoSensor: 10029 ,
								 MultiSensor: 10030 ,
						},
					},
				},
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
			 Mobility: {
					 Movable: {
							 ExternallyPropelled: 10033 ,
							 SelfPropelled: 10034 ,
					},
					 Immovable: 10035 ,
			},
			 Cardinality: {
					 SingleObject: 10036 ,
					 GroupOfObjects: 10037 ,
			},
		},
		 Task: {
				 Counting: 10038 ,
				 Tracking: 10039 ,
				 BehaviourRecognition: {
					 IntrusionDetection: 10040,
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
		 LongTermTracking: 10045,
		 Clustering: {
				 DistanceRGBAnd3DClustering: 10046 ,
				 FastClustering: 10047 ,
				 KMeansClustering: 10048 ,
		},
		 ClassificationModule: {
			 ClassificationStandard: {
				 Density: 10049,
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
			 Split: {
					 SplitStandard: 10058 ,
					 SplitGravityCenter: 10059 ,
			},
			 Contour: 10060,
		},
		 ReferenceImageUpdating: 10061,
		 ImageAcquisition: 10062,
		 TrackingDataFusion: 10063,
		 SegmentationModule: {
			 Threshold: {
					 LowThreshold: 10064 ,
					 MidThreshold: 10065 ,
					 HighThreshold: 10066 ,
			},
			 KernelFunction: {
				 ObjectAspect: {
						 Edge: 10067 ,
						 Region: 10068 ,
				},
				 ColorType: {
						 GreyLevel: 10069 ,
						 SystemColor: 10070 ,
				},
				 ShadowElimination: 10071,
			},
			 TraversalAlgorithm: {
				 GridStep: {
						 HighGridStep: 10072 ,
						 LowGridStep: 10073 ,
				},
				 TraversalParameters: {
						 WithWindow: 10074 ,
						 WithMask: 10075 ,
						 Fast: 10076 ,
				},
			},
			 SegmFineTune: 10077,
		},
		 ScenarioRecognition: 10078,
		 FrameToFrameTracking: 10079,
	},
}

};

