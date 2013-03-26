var flare = {
aggVS: {
	 VSApplicationRequirement: {
		 QualityOfService: {
				 ComputerLoad: 10000 ,
				 ResponseTime: 10001 ,
				 Quality: {
						 Sensitivity: 10002 ,
						 Precision: {
							 LessPrecision: 10003,
						},
				},
		},
		 ObjectOfInterest: {
			 Sort: {
					 Natural: {
						 Person: 10004,
					},
					 Manufactured: {
						 Vehicle: 10005,
					},
			},
			 Mobility: {
					 Movable: {
							 ExternallyPropelled: 10006 ,
							 SelfPropelled: 10007 ,
					},
					 Immovable: 10008 ,
			},
			 Cardinality: {
					 SingleObject: 10009 ,
					 GroupOfObjects: 10010 ,
			},
		},
		 Scene: {
			 Environment: {
				 LightingConditions: {
						 Indoors: 10011 ,
						 Outdoors: {
							 TimeOfDay: {
									 Day: 10012 ,
									 Night: 10013 ,
							},
						},
				},
				 LightingType: {
						 ArtificialLight: 10014 ,
						 NaturalLight: 10015 ,
				},
				 Noise: {
					 BackgroundMovement: {
							 SandOrDust: 10016 ,
							 SeaOceanWaves: 10017 ,
							 VegetationMovement: 10018 ,
					},
					 LightingVariation: {
							 Flashes: 10019 ,
							 HeadLight: 10020 ,
							 Shadows: 10021 ,
					},
				},
			},
			 APrioriKnowledge: {
				 BehaviourLibrary: 10022,
				 DeploymentArchitecture: {
					 ClientDefined: 10023,
					 Sensor: {
						 SensorView: {
								 SideView: 10024 ,
								 TopView: 10025 ,
								 NormalView: 10026 ,
						},
						 SensorProperty: {
								 MonoSensor: 10027 ,
								 MultiSensor: 10028 ,
						},
						 Camera: {
							 CameraType: {
									 BlackAndWhite: 10029 ,
									 Color: 10030 ,
									 Infrared: 10031 ,
							},
							 DepthOfField: 10032,
							 FramesPerSecond: {
									 HighFrameRate: {
										 VingtCinqFrSec: 10033,
									},
									 LowFrameRate: 10034 ,
									 IntermediateFrameRate: 10035 ,
							},
							 FieldOfView: {
									 Large: 10036 ,
									 Narrow: 10037 ,
							},
							 Resolution: 10038,
						},
					},
					 ProgrammerDefined: 10039,
					 Server: 10040,
				},
				 Scenery3DModel: 10041,
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
		 ImageAcquisition: 10045,
		 SegmentationModule: {
			 KernelFunction: {
				 ObjectAspect: {
						 Edge: 10046 ,
						 Region: 10047 ,
				},
				 ColorType: {
						 GreyLevel: 10048 ,
						 SystemColor: 10049 ,
				},
				 ShadowElimination: 10050,
			},
			 TraversalAlgorithm: {
				 GridStep: {
						 HighGridStep: 10051 ,
						 LowGridStep: 10052 ,
				},
				 TraversalParameters: {
						 WithWindow: 10053 ,
						 WithMask: 10054 ,
						 Fast: 10055 ,
				},
			},
			 Threshold: {
					 LowThreshold: 10056 ,
					 MidThreshold: 10057 ,
					 HighThreshold: 10058 ,
			},
			 SegmFineTune: 10059,
		},
		 TrackingDataFusion: 10060,
		 ClassificationModule: {
			 Merge: {
					 FusionGravityCenter: 10061 ,
					 FusionStandard: 10062 ,
			},
			 ClassificationStandard: {
				 Density: 10063,
			},
			 Contour: 10064,
			 Model: {
					 ModelType: {
							 OmegaModel: 10065 ,
							 ThreeDModel: 10066 ,
					},
					 Ellipse: 10067 ,
					 Shape: 10068 ,
					 Parallelepiped: 10069 ,
					 GravityCenter: 10070 ,
			},
			 Split: {
					 SplitStandard: 10071 ,
					 SplitGravityCenter: 10072 ,
			},
		},
		 Clustering: {
				 DistanceRGBAnd3DClustering: 10073 ,
				 FastClustering: 10074 ,
				 KMeansClustering: 10075 ,
		},
		 ScenarioRecognition: 10076,
		 LongTermTracking: 10077,
		 ReferenceImageUpdating: 10078,
		 FrameToFrameTracking: 10079,
	},
}

};

